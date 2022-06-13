package com.nicekkong.kotlinboot.entity

import javax.persistence.*


@Entity
@Table(name = "tb_employee")
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    @Column
    var name:String? = null,
    @Column
    var deptName:String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="dept_id")
    var dept:Department? = null
) {
    override fun toString() = "Employee : $id $name"

    fun addDept(dept:Department) {
        if(this.dept != null) {
            this.dept!!.employees.remove(this)
        }
        this.dept = dept

        if(!dept.employees.contains(this)) {
            dept.employees.add(this)
        }
    }
}