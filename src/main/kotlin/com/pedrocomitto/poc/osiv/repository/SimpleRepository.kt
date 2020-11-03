package com.pedrocomitto.poc.osiv.repository

import com.pedrocomitto.poc.osiv.domain.SimpleEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SimpleRepository : JpaRepository<SimpleEntity, Long>