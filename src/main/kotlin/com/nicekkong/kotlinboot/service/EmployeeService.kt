package com.nicekkong.kotlinboot.service

import com.nicekkong.kotlinboot.dto.response.CommonResponse
import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.entity.Student
import com.nicekkong.kotlinboot.repository.EmployeeRepository
import com.nicekkong.kotlinboot.repository.findId
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
//                this.id = employee.id.toLong()
                this.name = employee.name
            }
        }

        val s1 = Student().apply {
            id = 1
            this.name= "asdf"
        }

        val e1 = Employee().apply {
            this.name = "asdf"
        }



        return employeeResponse

    }

    fun findById(id: Long): CommonResponse<EmployeeDto> {

        val employee = employeeRepository.findById(id).get()

        var employeeResponse:CommonResponse<EmployeeDto> = CommonResponse()
        employeeResponse.let { emp ->
            emp.code = "0000"
            emp.message = "Success"
            emp.body = EmployeeDto().apply {
                this.id = employee.id
                this.name = employee.name
            }
        }
        return employeeResponse


        }


        fun searchById(id:Long): EmployeeDto? {
            return employeeRepository.findId(id).let{
                EmployeeDto().apply {
                    this.id = it.id
                    this.name = it.name
                }
            }

//            return employeeRepository.findId(id).orElse(null)?.let {
//                EmployeeDto().apply {
//                    this.id = it.id
//                    this.name = it.name
//                }
//            }
        }
}