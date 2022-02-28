package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByNameContains(name:String): Employee
}