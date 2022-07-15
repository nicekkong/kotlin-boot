package com.nicekkong.kotlinboot.zample.controller

import kotlinx.coroutines.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class CoroutineController(

) {

    @GetMapping("/foos")
    fun getFoo(): String {

        val start = System.currentTimeMillis()
        println("start: ${start}s")
        var result = runBlocking {
//            CoroutineScope(Dispatchers.Default).async {
//                test1() + test2()
//            }


            doWorld()
        }
        println("end: ${(System.currentTimeMillis() - start)/1000F}s")
        return result.toString()
    }


    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        var num1 = 0
        launch {
            delay(3000L)
            println("World 2")
            num1 = 111
        }
        var num2 = 0
        launch {
            delay(2000L)
            println("World 1")
            num2 = 222
        }
        println("Hello") 
        print(num1 + num2)
    }


    private suspend fun test1(): Int {
        coroutineScope {
            delay(2000)
            println("test1 call")
        }
        return 1111
    }

    private suspend fun test2(): Int {
        coroutineScope {
            delay(3000)
            println("test2 call~!!")
        }
        return 2222
    }

}