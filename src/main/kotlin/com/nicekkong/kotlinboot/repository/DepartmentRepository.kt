package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository: JpaRepository<Department, Long> {

    fun existsByDeptName(deptName:String):Boolean

    fun findByDeptName(deptName:String):Department

}