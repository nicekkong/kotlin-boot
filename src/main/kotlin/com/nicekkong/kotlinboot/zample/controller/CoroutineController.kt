package com.nicekkong.kotlinboot.zample.controller

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class CoroutineController(

) {

    @GetMapping("/foos")
    fun getFoo(): String {


        println("start: ${LocalDateTime.now()}")
        var result = runBlocking {
                test1() + test2()
        }
        println("end: ${LocalDateTime.now()}")
        return result.toString()
    }


    private suspend fun test1(): Int {
        delay(2000)
        println("test1 call")
        return 1111
    }

    private suspend fun test2(): Int {
        delay(3000)
        println("test2 call~!!")
        return 2222
    }

}