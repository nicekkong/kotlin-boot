package com.nicekkong.kotlinboot.zample.repository

import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository: JpaRepository<Department, Long> {

    fun existsByDeptName(deptName:String):Boolean

    fun findByDeptName(deptName:String):Department

}