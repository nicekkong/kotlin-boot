package com.nicekkong.kotlinboot.controller

import com.nicekkong.kotlinboot.dto.response.CommonResponse
import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
@RestController
class IndexController (
    val employeeService: EmployeeService) {

    @GetMapping("/save/emp")
    fun saveEmp(@RequestParam(value = "name")name:String) = employeeService.saveEmployee(name)

    @GetMapping("/find/emp")
    fun findEmp(@RequestParam(value = "name")name:String): CommonResponse<EmployeeDto> {
        val result = employeeService.findEmployee(name)
        return result
    }
}

