# Spring's Open Session In View

The spring.jpa.open-in-view is enabled by default. It basically opens a new Hibernate Session at the beginning of the request, but not necessarily connected to the database.

In simple things it works fine, but in real world, when the business logic starts to grow up, we can have serious performance problems.

If we have requests to another remote services and it is slower than usual, we could probably run out of connections from the pool. 
Our connection will be hold during all the requests to another services.

And now the main trick of this approach: Even if we add a @Transactional on our truly transactional points, we still have our connection on hold until the end of the request scope.

It happens because as I said before, at the beginning of the request, our hibernate session is opened and it closes only when the request finishes.

In this simple example, we can check that.

- We have a Thread.sleep() on our controller to simulate some slower point OUT OF our transactional points.
- We have just one connection available from the pool

1. Open src/main/resources/application.yml and set the spring.jpa.open-in-view to "false"
2. Run the application
3. Open the terminal and execute ``` $ curl localhost:8080/simple & curl localhost:8080/simple/1 ```

Everything works fine. Our @Transactional annotations ensures the beginning and the commit of the transactions.

But now, let's try with Open Session In View enabled, **which is the default for all applications that does not have been explicitly disabled it**.

1. Open src/main/resources/application.yml and set the spring.jpa.open-in-view to "true"
2. Run the application
3. Open the terminal and execute ``` $ curl localhost:8080/simple & curl localhost:8080/simple/1 ``` 

It failed, right? Even with Thread.sleep() out of our transactional points, the second request cannot get the connection because it was held by the first request. 

Another important point is that when OSIV is active, there is always a session in the current request scope. Disabling it, will prevent extras queries to fetch any lazy relationships.

Besides that, now we will must handle LazyInitializationExceptions but there are may ways to solve it. In addition to the way that I did on this example, using Hibernate.initialize(), there are many others. But also there are two quick and efficient ways to solve it. These are @EntityGraph ou a simple JOIN FETCH.

pedrocomitto
