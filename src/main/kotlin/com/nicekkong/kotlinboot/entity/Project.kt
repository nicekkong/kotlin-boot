package com.nicekkong.kotlinboot.entity

import javax.persistence.*

@Entity
@Table(name = "tb_project")
class Project (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?,

    @Column(nullable = false)
    var title:String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", nullable = true)
    var employee: Employee?
)