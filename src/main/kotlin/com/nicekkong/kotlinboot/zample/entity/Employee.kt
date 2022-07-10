package com.nicekkong.kotlinboot.zample.entity

import com.nicekkong.kotlinboot.entity.common.AuditDateEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*


@Entity
@Table(name = "tb_employee")
@DynamicUpdate  // 변경된 필드만 Update
class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    @Column
    var name:String? = null,
    @Column
    var job:String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="dept_id", referencedColumnName = "id")
    var dept: Department? = null
) : AuditDateEntity() {
    override fun toString() = "Employee : $id $name"

    fun addDept(dept: Department) {
        if(this.dept != null) {
            this.dept!!.employees?.remove(this)
        }
        this.dept = dept

        if(dept.employees?.contains(this) == true) {
            dept.employees?.add(this)
        }
    }
}