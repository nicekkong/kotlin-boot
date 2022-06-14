package com.nicekkong.kotlinboot.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
class CommonDateEntity (
    @Column
    val creDate: LocalDateTime = LocalDateTime.now(),

    @Column
    var updDate: LocalDateTime? = null
)