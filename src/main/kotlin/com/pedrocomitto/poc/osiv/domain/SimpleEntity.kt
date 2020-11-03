package com.pedrocomitto.poc.osiv.domain

import javax.persistence.*

@Entity
@Table(name = "SIMPLE")
data class SimpleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    val param1: String,
    @OneToMany
    @JoinColumn(name = "simple_id")
    val childs: List<SimpleChildEntity>? = null
)