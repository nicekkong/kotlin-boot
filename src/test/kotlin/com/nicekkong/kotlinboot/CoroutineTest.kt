package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.zample.entity.Employee
import com.nicekkong.kotlinboot.zample.repository.EmployeeRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.function.ServerResponse.async
import java.time.LocalDate

class CoroutineTest {

    @Test
    fun launchSeveralCoroutinesInDifferentScopes() {
        val start:Long = System.currentTimeMillis()

        runBlocking{
//        GlobalScope.launch {
            delay(1000)
            println("1: ${(System.currentTimeMillis() - start)/1000F}s")
            println("1:[${Thread.currentThread().name} - World")
        }
        println("2: ${(System.currentTimeMillis() - start)/1000F}s")
        println("2:[${Thread.currentThread().name} => Hello")

        runBlocking {
            delay(2000)
            println("3: ${(System.currentTimeMillis() - start)/1000F}s")
            println("3:[${Thread.currentThread().name} => runblocking~!!!")
        }

        println("END: ${(System.currentTimeMillis() - start)/1000F}s")
    }


    @Test
    fun `test coroutines`() {
        println("1 before runBlocking")
        // runBlocking : 현재 쓰레드를 블록하고, 내부 코루틴이 종료될 때까지 블록한다.
        // 코루틴 영역의 시작
        runBlocking {
            println("2 before launch")
            // 코루틴 시작
            GlobalScope.launch {
                println("3G Hello ")
                delay(300L)
                println("4G World")
            }
            println("5 after lanuch")
        }
        println("6 after runBlocking")

        println("================================================================")

        println("1 before runBlocking")
        // runBlocking : 현재 쓰레드를 블록하고, 내부 코루틴이 종료될 때까지 블록한다.
        // 코루틴 영역의 시작
        runBlocking {
            println("2 before launch")
            // 코루틴 시작
            runBlocking {
                println("3 Hello ")
//                delay(200L)
                println("4 World")
            }
            println("5 after lanuch")
        }

        runBlocking {
            println("33 Hello ")
//            delay(200L)
            println("44 World")
        }


        println("6 after runBlocking")

        println("================================================================")

        println("1 before runBlocking")
        runBlocking {
            println("2 before launch")
            async {
                println("3 Hello ")
                delay(200L)
                println("4 World")
            }.await()
            println("5 after lanuch")
        }
        println("6 after runBlocking")
    }
}