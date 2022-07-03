package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.repository.querydsl.EmployeeSupport
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeQueryDslRepository : JpaRepository<Employee, Long>, EmployeeSupport