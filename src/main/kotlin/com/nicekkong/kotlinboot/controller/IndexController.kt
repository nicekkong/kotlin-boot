package com.nicekkong.kotlinboot.controller

import com.nicekkong.kotlinboot.dto.response.CommonResponse
import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class IndexController (
    val employeeService: EmployeeService,
    val restTemplate:RestTemplate) {

    @GetMapping("/save/emp")
    fun saveEmp(@RequestParam(value = "name")name:String) = employeeService.saveEmployee(name)

    @GetMapping("/find/emp")
    fun findEmp(@RequestParam(value = "name") name: String): CommonResponse<EmployeeDto> {
        return employeeService.findEmployee(name)
    }


    @GetMapping("/find/emp2")
    fun findEmp2(@RequestParam(value = "name")name:String): ResponseEntity<CommonResponse<EmployeeDto>> {
        val result = employeeService.findEmployee(name)

        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping(value = ["/find/id"])
    fun findById(@RequestParam(value="id")id:Long): ResponseEntity<CommonResponse<EmployeeDto>> {
        val result = employeeService.findById(id)

        return ResponseEntity(result, HttpStatus.OK)


    }

    @GetMapping(value = ["/findId"])
    fun findId(@RequestParam(value="id")id:Long): ResponseEntity<CommonResponse<EmployeeDto>> {
        val test = IntRange(1, 10)

        test.takeIf{id !in test}?.let{
            println("TEST Success~!!! $test")
        }



        return ResponseEntity(HttpStatus.OK)
    }

}

