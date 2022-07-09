package com.nicekkong.kotlinboot.zample.entity

import com.nicekkong.kotlinboot.entity.common.AuditDateEntity
import java.util.*
import javax.persistence.*


@Entity
@Table(name="tb_department")
class Department (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,

    @Column
    var deptName:String? = null,

    @OneToMany(mappedBy = "dept")
    var employees: MutableSet<Employee>? = TreeSet()
        ): AuditDateEntity() {

    fun addEmployee(emp: Employee) {
        this.employees?.add(emp)
        emp.dept = this
    }
}