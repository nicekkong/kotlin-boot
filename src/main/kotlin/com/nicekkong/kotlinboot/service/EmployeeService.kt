package com.nicekkong.kotlinboot.service

import com.nicekkong.kotlinboot.dto.response.CommonResponse
import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.entity.Student
import com.nicekkong.kotlinboot.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository) {

    fun saveEmployee(name:String) {
        val employee = Employee().apply {
            this.name = name
        }
        employeeRepository.save(employee)
    }

    fun findEmployee(name:String): CommonResponse<EmployeeDto> {

        val employee = employeeRepository.findByNameContains(name)
        println(employee.name)

        val emp = Employee().apply {
            this.name = "nicekkong"
        }

        var employeeResponse:CommonResponse<EmployeeDto> = CommonResponse()
        employeeResponse.let { emp ->
            emp.code = "0000"
            emp.message = "Success"
            emp.body = EmployeeDto().apply {
                this.id = employee.id.toLong()
                this.name = employee.name
            }
        }
        return employeeResponse

    }
}