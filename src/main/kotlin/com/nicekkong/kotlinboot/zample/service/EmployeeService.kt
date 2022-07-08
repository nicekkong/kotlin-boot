package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.zample.dto.request.EmployeeRequest
import com.nicekkong.kotlinboot.zample.dto.response.CommonResponse
import com.nicekkong.kotlinboot.zample.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.zample.repository.DepartmentRepository
import com.nicekkong.kotlinboot.zample.repository.EmployeeRepository
import com.nicekkong.kotlinboot.zample.repository.MappingRepository
import com.nicekkong.kotlinboot.zample.repository.findId
import com.nicekkong.kotlinboot.zample.entity.Department
import com.nicekkong.kotlinboot.zample.entity.Employee
import com.nicekkong.kotlinboot.zample.entity.Mapping
import com.nicekkong.kotlinboot.zample.entity.Student
import mu.KotlinLogging
import org.springframework.stereotype.Service



@Service
class EmployeeService(
    val employeeRepository: EmployeeRepository,
    val departmentRepository: DepartmentRepository,
    val mappingRepository: MappingRepository
) {

    private val logger = KotlinLogging.logger{}

    fun saveEmployee(name: String) {

        logger.info { "Name ::: $name"}

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


    fun saveEmpInfo(empInfo: EmployeeRequest) {

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


    fun saveProject() {
        val deptment:Department = Department(
            deptName = "IT팀"

        )
        val emp:Employee = Employee(
            name = "기획팀",
            job = "Developer"
        )
        emp.addDept(deptment)


        var ttt:Array<Int>
        var ttt1:List<Int>



        val funT = {v1:Int, v2:Int -> v1 + v2}
        val ggg = funT(1, 2)
        print(ggg)


        var m = Mapping(senderId = 1L)
        m.senderId = 2L
        mappingRepository.save(m)

    }

    open class Fruit
    class Apple:Fruit()
    class Banana:Fruit()


    fun copyFromTo(from: Array<out Fruit>, to: Array<in Fruit>) {
        for (i in from.indices) {
            to[i] = from[i]
        }
    }

    fun copyFromTo2(from: Array<Fruit>, to: Array<Fruit>) {
        for (i in from.indices) {
            to[i] = from[i]
        }
    }



    fun countMapping():Int = mappingRepository.findAll().size


}