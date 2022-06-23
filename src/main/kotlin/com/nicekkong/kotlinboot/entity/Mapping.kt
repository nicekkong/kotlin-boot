package com.nicekkong.kotlinboot.entity

import javax.persistence.*

@Entity
class Mapping (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var senderId: Long,
        )