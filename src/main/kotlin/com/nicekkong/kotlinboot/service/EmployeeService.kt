package com.nicekkong.kotlinboot.service

import com.nicekkong.kotlinboot.dto.request.EmployeeRequest
import com.nicekkong.kotlinboot.dto.response.CommonResponse
import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.entity.Department
import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.entity.Student
import com.nicekkong.kotlinboot.repository.DepartmentRepository
import com.nicekkong.kotlinboot.repository.EmployeeRepository
import com.nicekkong.kotlinboot.repository.findId
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository,
    val departmentRepository: DepartmentRepository,
) {

    fun saveEmployee(name: String) {
        val employee = Employee().apply {
            this.name = name
        }
        employeeRepository.save(employee)
    }

    fun findEmployee(name: String): CommonResponse<EmployeeDto> {

        val employee = employeeRepository.findByNameContains(name)
        println(employee.map { it.name }.orElse(null))

        val emp = Employee().apply {
            this.name = "nicekkong"
        }

        var employeeResponse: CommonResponse<EmployeeDto> = CommonResponse()
        employeeResponse.let { emp ->
            emp.code = "0000"
            emp.message = "Success"
            emp.body = EmployeeDto().apply {
//                this.id = employee.id.toLong()
                this.name = employee.takeIf { it.isPresent }?.let { name }
            }
        }

        val s1 = Student().apply {
            id = 1
            this.name = "asdf"
        }

        val e1 = Employee().apply {
            this.name = "asdf"
        }

        return employeeResponse

    }

    fun findById(id: Long): CommonResponse<EmployeeDto> {

        val employee = employeeRepository.findById(id).get()

        var employeeResponse: CommonResponse<EmployeeDto> = CommonResponse()
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


    fun searchById(id: Long): EmployeeDto? {
        return employeeRepository.findId(id).let {
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


    fun saveEmpInfo(empInfo:EmployeeRequest) {

        val deptInfo = empInfo
            .takeUnless {
                departmentRepository.existsByDeptName(it.deptName)
            }?.let {
                departmentRepository.save(
                    Department().apply {
                        deptName = empInfo.deptName
                    }
                )
            }?: departmentRepository.findByDeptName(empInfo.deptName)

        val emp:Employee = Employee().apply {
            name = empInfo.name
            job = empInfo.job
        }
        emp.addDept(deptInfo)
        employeeRepository.save(emp)
    }
}