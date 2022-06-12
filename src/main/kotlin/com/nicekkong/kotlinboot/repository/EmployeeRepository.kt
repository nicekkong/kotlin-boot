package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


fun EmployeeRepository.findByName(name:String):Employee = findByNameContains(name)
fun EmployeeRepository.findId(id:Long):Employee = findById(id).orElse(null)

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByNameContains(name:String): Employee
}