package com.pedrocomitto.poc.osiv.domain

import javax.persistence.*

@Entity
@Table(name = "SIMPLE_CHILD")
data class SimpleChildEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    val param1: String
)
