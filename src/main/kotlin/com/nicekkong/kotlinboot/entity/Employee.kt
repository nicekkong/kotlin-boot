package com.nicekkong.kotlinboot.entity

import javax.persistence.*


@Entity
@Table(name = "tb_employee")
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0,
    @Column
    var name:String? = null
) {
    override fun toString() = "Employee : $id $name"
}