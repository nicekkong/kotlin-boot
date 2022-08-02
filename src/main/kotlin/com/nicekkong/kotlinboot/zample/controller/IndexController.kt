package com.nicekkong.kotlinboot.zample.controller

import com.nicekkong.kotlinboot.zample.dto.request.EmployeeRequest
import com.nicekkong.kotlinboot.zample.dto.response.ApiResponse
import com.nicekkong.kotlinboot.zample.dto.response.CommonResponse
import com.nicekkong.kotlinboot.zample.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.zample.dto.response.EmployeeResponse
import com.nicekkong.kotlinboot.exception.MyDetailException
import com.nicekkong.kotlinboot.exception.MyErrorCode
import com.nicekkong.kotlinboot.exception.UserMessageException
import com.nicekkong.kotlinboot.zample.service.EmployeeService
import com.nicekkong.kotlinboot.zample.service.SampleService
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@Slf4j
@RestController
class IndexController (
    val employeeService: EmployeeService,
    val restTemplate:RestTemplate,
    val sampleService: SampleService

    ) {

//    private val log = logger()

    private val logger = KotlinLogging.logger {  }

    @Value("\${my.info.name}")
    private lateinit var myName:String

    @Value("\${my.info.email}")
    private lateinit var myEmail:String

    @GetMapping("/")
    fun index(): String {
        return "OK"
    }

    @GetMapping("/save/emp")
    fun saveEmp(@RequestParam(value = "name")name:String) = employeeService.saveEmployee(name)

    @GetMapping("/find/emp")
    fun findEmp(@RequestParam(value = "name") name: String): CommonResponse<EmployeeDto> {
        logger.debug { "DEBUG~!!!!!!!" }
        logger.info("call find/emp~!! name: {}", name)
        logger.info{ "myName ===> $myName"}
        logger.info{ "myEmail ===> $myEmail"}
        logger.error{"ERROR~!!!!!!!"}
        logger.debug{"DEBUG~!!!!!!!"}

        sampleService.myBatis()

//        try {
//            throw UserMessageException("runtimeException")
//        } catch(e:Exception) {
//
//            logger.info("catch~!!!!!")
//
//        }

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


    @GetMapping(value = ["/get/emp/info"])
    fun getEmpInfo(empInfo: EmployeeRequest): Map<String, String> {

        logger.info(empInfo.toString())
        logger.info("GET:::empInfo.deptName ===> ${empInfo.deptName}")

        return mapOf("code" to "0000")

    }

    @PostMapping(value = ["/post/emp/info"])
    fun postEmpInfoByPost(@RequestBody empInfo: EmployeeRequest)
                            : ResponseEntity<CommonResponse<EmployeeResponse>>{

        logger.info(empInfo.toString())

        logger.info("POST:::empInfo.deptName ===> ${empInfo.deptName}")


        val empInfo = EmployeeResponse(
            id = 99L,
            name = empInfo.name,
            job = empInfo.job,
        )

        val result = CommonResponse(
            code = "00",
            message = "Success",
            body = empInfo,
        )
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(value = ["/save/emp/info"])
    fun saveEmpInfo(@RequestBody empInfo: EmployeeRequest)
                        : ResponseEntity<CommonResponse<Nothing>> {


        employeeService.saveEmpInfo(empInfo)

        val result = CommonResponse(
            code = "00",
            message = "Success",
            body = null,
        )
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping(value = ["/update/emp"])
    fun updateEmp(@RequestParam(value = "id")id:Long,
                  @RequestParam(value = "name")name:String) {

        employeeService.updateEmp(id, name)


    }

    @PostMapping(value = ["/save/emp"])
    fun saveEmp(@RequestBody empReq: EmployeeRequest): ResponseEntity<CommonResponse<Unit>> {
        employeeService.saveEmpInfo(empReq)
        return ResponseEntity(CommonResponse(), HttpStatus.OK)
    }

    @GetMapping(value = ["/find/emp/id1"])
    fun findId1(@RequestParam(value = "id")id:String
                            ): ResponseEntity<ApiResponse<EmployeeDto>> {
        val employeeDto = employeeService.searchById(id.toLong())

        throw Exception("Exception~!!! ")
        return ResponseEntity.ok(ApiResponse.success(employeeDto))
    }

    @GetMapping(value = ["/find/emp/id2"])
    fun findId2(@RequestParam(value = "id")id:String
    ): ResponseEntity<ApiResponse<EmployeeDto>> {
        val employeeDto = employeeService.searchById(id.toLong())

        throw UserMessageException("50002", "UserMessageException~!!! ")
        return ResponseEntity.ok(ApiResponse.success(employeeDto))
    }

    @GetMapping(value = ["/find/emp/id3"])
    fun findId3(@RequestParam(value = "id")id:String
    ): ResponseEntity<ApiResponse<EmployeeDto>> {
        val employeeDto = employeeService.searchById(id.toLong())
//        throw MyException(MyErrorCode.MY_ERROR)
        throw MyDetailException(MyErrorCode.MY_ERROR, "detail error message")
        return ResponseEntity.ok(ApiResponse.success(employeeDto))
    }

    @GetMapping(value = ["/success"])
    fun success(): ResponseEntity<ApiResponse<Nothing>> {
        throw UserMessageException("9999", "error message")
        return ResponseEntity(ApiResponse.success(), HttpStatus.OK)
    }

    @GetMapping(value = ["/fail"])
    fun fail(): ResponseEntity<ApiResponse<Nothing>> {
        throw UserMessageException("9999", "error message", "detail error message")

        return ResponseEntity(ApiResponse.fail(), HttpStatus.NOT_FOUND)
    }
}

