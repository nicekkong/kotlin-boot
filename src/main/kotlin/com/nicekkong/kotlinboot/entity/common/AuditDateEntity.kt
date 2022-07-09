package com.nicekkong.kotlinboot.entity.common

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditDateEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    var creDate: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(updatable = true)
    var updDate: LocalDateTime? = null
        protected set

}