package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.repository.EmployeeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class TestKCode {

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

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

//        val map: List<Int> = students.filter { it == "nicekk1ong" }
//            .ifEmpty { listOf("None") }
//            .map { it.length }

//        println(map)
    }

    @Test
    fun `test runBloking()`() {
        println("Before")

    }



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

    @Test
    fun saveEmp() {
        val emp = Employee().apply{
            this.name = "nicekkong"
        }
        employeeRepository.save(emp)
    }

    @Test
    fun `test withIndex()`() {

        val strings = arrayOf("this", "is", "an", "array", "of", "strings")
        for((i, v) in strings.withIndex()){
            println("Index $i maps to $v")
        }

        for(i in strings) {
            println("$i")
        }



    }

    @Test
    fun `test collection map`() {
        val keys = 'a'..'f'
        for(v in keys) {
            println(v)
        }
        val map = keys.associateWith {
                    it.toString().repeat(5)
                    .replaceFirstChar {c -> c.uppercase() }
        }
        println(map)
    }


    data class Product(val name:String, var price:Double, var onSale:Boolean = false)

    @Test
    fun `test product`() {

        val p1 = Product("Kong1", 1.234, true)
        val p2 = Product("Kong2", 1.234, true)
        val p3 = Product("Kong3", 1.234, true)
        val products = listOf(p1, p2, p3)

        val joinToString = products.filter { it.onSale }
                                    .joinToString(separator = "|") { it.name }

        println(products.filter{it.onSale}.ifEmpty { listOf("A","B") }.joinToString(separator = ","){it.toString()})
        println(joinToString)


        val sortedWith = products.sortedWith(
//            compareBy<Product> { it.name }.reversed().thenBy { it.price }
            compareBy<Product> { it.name }.thenBy { it.price }.reversed()

        )

        println(sortedWith)

    }

    @Test
    fun `instance filter`() {

        val lists = listOf("a", "b", 1, 2, 3, LocalDate.now())

        println(lists.filterIsInstanceTo(mutableListOf<LocalDate>()))
    }

    @Test
    fun `run function`() {
        val regex = run {
            val port = 8000
            val name = "nicekkong"
            name + port
        }

        println(regex)

    }

    @Test
    fun `takeIf test`() {

        val ranges = 1..10

        val a = 50

        a.takeIf { a in ranges }?.let {
            println("a ===> ${it}")
        }
    }

    @Autowired
    lateinit var employeeSupportImpl: EmployeeSupportImpl

    @Test
    fun `test queryDsl`() {

        for (employee in employeeSupportImpl.getEmp()) {
            println("${employee.name} ::: ${employee.job}")
        }
    }


//    class MyTests: ShouldSpec(){
//        init {
//            "string size" {
//                "hello".length shouldBe 5
//            }
//        }
//
//    }
//
//    @Test
//    fun `test test`() {
//
//        var test = MyTests()
//
//    }




}