package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class TestKCode {

    @Test
    fun `withIndex() Test`() {

        val numArray = "nicekkong 1 2 3".split(" ")

        for((i, v) in numArray.withIndex()) {
            println("${i} === > $v")
            println("===============")
        }

//        TODO(reason = "Nice Job~!!!")
    }

    @Test
    fun `associate test`() {
        val keys = 'a'..'f'
        val map1 = keys.associate { it to it.toString().repeat(5).capitalize() }
        val map2 = keys.associateWith { it.toString().repeat(5).capitalize() }

        println(map2)
    }

    @Test
    fun `ifEmpty test`() {
        val students = listOf("nicekkong", "KBI", "John")

        val map: List<Int> = students.filter { it == "nicekk1ong" }
            .ifEmpty { listOf("None") }
            .map { it.length }

        println(map)
    }

    @Test
    fun `test runBloking()`() {
        println("Before")

    }

//    @Autowired
//    lateinit var employeeRepository: EmployeeRepository

//    @Test
//    fun `repository test`() {
//
//        var employee = Employee(1, "nicekkong").apply{
//            name = "nicekkong"
//        }
//        employeeRepository.save(employee)
//
//        val findByNameContains = employeeRepository.findByNameContains("nicek")
//        println(findByNameContains)
//    }
}