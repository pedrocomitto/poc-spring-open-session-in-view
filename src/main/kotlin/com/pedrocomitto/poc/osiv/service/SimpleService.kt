package com.pedrocomitto.poc.osiv.service

import com.pedrocomitto.poc.osiv.domain.SimpleEntity
import com.pedrocomitto.poc.osiv.repository.SimpleRepository
import org.hibernate.Hibernate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SimpleService(
    private val simpleRepository: SimpleRepository
) {

    @Transactional(readOnly = true)
    fun findAll(): MutableList<SimpleEntity> =
        simpleRepository.findAll()
            .apply {
                forEach { Hibernate.initialize(it.childs) } // not the best approach. study purposes only
            }

    @Transactional(readOnly = true)
    fun findOne(id: Long): SimpleEntity =
        simpleRepository.findById(id)
            .orElseThrow { throw RuntimeException() }
            .apply { Hibernate.initialize(childs) } // not the best approach. study purposes only

}
