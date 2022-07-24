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
import kotlinx.coroutines.*
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.*


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


    @Transactional  // for dirty checking
    fun updateEmp(id: Long, name: String) {
        employeeRepository.findById(id).get().let{
            it.name = name
//            employeeRepository.save(it)
        }
    }
    
    fun getEmpName():String {
        val start = System.currentTimeMillis()

        val awaitAll: List<String>
        runBlocking {
            val emp5 = CoroutineScope(Dispatchers.IO).async {
//            val emp5 = async {
                getEmp5()
            }

            val emp7 = CoroutineScope(Dispatchers.IO).async {
//            val emp7 = async {
                getEmp7()
            }



            awaitAll = awaitAll(emp5, emp7)
        }
        logger.debug("Total Elapsed Time ::: ${(System.currentTimeMillis() - start)/1000F}s!!!")
        return "${awaitAll.component1()}:::${awaitAll.component2()}"
    }

    fun getEmpNameWithoutRunBlocking() :String {
        val emp5Name = runBlocking{
            val emp5 = CoroutineScope(Dispatchers.IO).async {
                getEmp5()
            }
            emp5.await()
        }

        val emp7Name = runBlocking{
            val emp7 = async {
                getEmp7()
            }
            emp7.await()
        }
        return "$emp5Name ::: $emp7Name"

    }


    fun getEmpNameSleep(): String {
        val start = System.currentTimeMillis()
        val emp5 = getEmp55()
        val emp7 = getEmp77()

        logger.debug("Total Elapsed Time ::: ${(System.currentTimeMillis() - start)/1000F}s!!!")
        return "$emp5 ::: $emp7"

    }


    suspend fun getEmp5(): String {
        val start = System.currentTimeMillis()
        logger.debug("emp5 Start ::: ${Thread.currentThread().name}")
        //        delay(2000)
        var num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        println("emp5 ==> $num")
        val get = employeeRepository.findById(5L).map { it.name }.get()

        logger.debug("555555 Total Elapsed Time ::: ${(System.currentTimeMillis() - start)/1000F}s!!!")
        return get

    }
    suspend fun getEmp7(): String {
        val start = System.currentTimeMillis()
        logger.debug("emp7 Start ::: ${Thread.currentThread().name}")
//        delay(2000)
        var num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        num = 0
        repeat(999999999) {
            num += it
        }
        println("emp7 ==> $num")
        val get = employeeRepository.findById(7L).map { it.name }.get()

        logger.debug("7777 Total Elapsed Time ::: ${(System.currentTimeMillis() - start)/1000F}s!!!")
        return get

    }


    fun getEmp55(): String {
        logger.debug("emp5 Start  ${Thread.currentThread().name}")
        Thread.sleep(3000)
        return employeeRepository.findById(5L).map{ it.name }.get()
    }
    fun getEmp77(): String {
        logger.debug("emp7 Start  ${Thread.currentThread().name}")
        Thread.sleep(2000)
        return employeeRepository.findById(7L).map{ it.name }.get()
    }
}