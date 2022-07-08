package com.nicekkong.kotlinboot.zample.repository

import com.nicekkong.kotlinboot.zample.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*




fun EmployeeRepository.findByName(name:String):Optional<Employee> = findByNameContains(name)
fun EmployeeRepository.findId(id:Long):Employee = findById(id).orElse(null)

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByNameContains(name:String): Optional<Employee>

}