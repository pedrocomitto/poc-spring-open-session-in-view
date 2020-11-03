package com.pedrocomitto.poc.osiv.controller

import com.pedrocomitto.poc.osiv.service.SimpleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/simple")
class SimpleController(
    private val simpleService: SimpleService
) {

    @GetMapping
    fun findAll() =
        simpleService.findAll()
            .also { Thread.sleep(2000) }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long) =
        simpleService.findOne(id)

}