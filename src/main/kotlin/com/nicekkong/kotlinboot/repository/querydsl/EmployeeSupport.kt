package com.nicekkong.kotlinboot.repository.querydsl

import com.nicekkong.kotlinboot.entity.Employee

interface EmployeeSupport {
    fun getEmp(): List<Employee>
}