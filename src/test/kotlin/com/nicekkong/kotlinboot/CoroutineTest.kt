package com.nicekkong.kotlinboot

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class CoroutineTest {

    @Test
    fun launchSeveralCoroutinesInDifferentScopes() {
        val start:Long = System.currentTimeMillis()

        // 현재 스레드를 블록 시키고, runBlocking 안에 코루틴이 완료 될 때까지 기다린다.
        runBlocking {
//        GlobalScope.launch {
            delay(1000)
            println("1: ${(System.currentTimeMillis() - start) / 1000F}s")
            println("1:[${Thread.currentThread().name} - World")
        }

        println("2: ${(System.currentTimeMillis() - start) / 1000F}s")
        println("2:[${Thread.currentThread().name} => Hello")

        runBlocking {
            println("[while][${Thread.currentThread().name} => Hello")
            var i = 0
            while (i < 100) {
                delay(100)
                println("\t\t\t[while]: $i")
                i++
            }
            println("[while] ${(System.currentTimeMillis() - start) / 1000F}s")
        }

        runBlocking {
            delay(2000)
            println("3: ${(System.currentTimeMillis() - start) / 1000F}s")
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
        // runBlocking : 현재 쓰레드를 블록하고, 내부 코루틴이 완료될 때까지 블록한다.
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


    @Test
    suspend fun `launch test`() {
        launch_co()
    }

    @Test
    fun launch_co() {

        val start = System.currentTimeMillis()

        var a1:Deferred<Int>
        var a = 0

        runBlocking {
            a1 = async {
                var i = 0
                while (i < 10) {
                    delay(20L)
                    i++
                    println("\t\t\t\t\t$i")
                }
                println("1: ${(System.currentTimeMillis() - start) / 1000F}s")
                println("1:[${Thread.currentThread().name} - $i")
                i
            }

            async {
                delay(100L)
                println("2: ${(System.currentTimeMillis() - start) / 1000F}s")
                println("2:[${Thread.currentThread().name} - nicekkong")
            }
//            job.join()
//        joinAll(job)
            a = a1.await()


        }
        println("a ===> $a")
    }

    @Test
    fun `async test`() {
        val start = System.currentTimeMillis()
        var result:Int
        runBlocking {
            val a1 = async {
                delay(100L)
                println("1: ${(System.currentTimeMillis() - start) / 1000F}s")
                100
            }
            val a2 = async(start = CoroutineStart.LAZY) {
                delay(300)
                println("2: ${(System.currentTimeMillis() - start) / 1000F}s")
                300
            }
//            println("a1 ====> ${a1.await()}")
//            println("a2 ===> ${a2.await()}")
            awaitAll(a1, a2)
            println("Before: ${(System.currentTimeMillis() - start) / 1000F}s")

            result = a1.getCompleted() + a2.getCompleted()
            println("After: ${(System.currentTimeMillis() - start) / 1000F}s")
        }
        println("result ===> $result")
        println("result: ${(System.currentTimeMillis() - start) / 1000F}s")
    }

    @Test
    fun `runBlocking test`() {
        val start = System.currentTimeMillis()
        runBlocking {
            println("Before: ${(System.currentTimeMillis() - start) / 1000F}s")
            val l1:Job = launch {
                println(c1())
                println("1: ${(System.currentTimeMillis() - start) / 1000F}s")
            }
            val l2:Job = launch {
                println(c2())
                println("2: ${(System.currentTimeMillis() - start) / 1000F}s")
            }
            // l1, l2가 종료되기를 기다린다.
//            joinAll(l1, l2)
            println("After: ${(System.currentTimeMillis() - start) / 1000F}s")
        }
    }

    suspend fun c1(): String {
        delay(2000)
        println("c1~!!!!!!")
        return "nicekkong1"
    }

    suspend fun c2(): String {
        delay(1000)
        println("c2~!!!!!!")
        return "nicekkong2"
    }

    @Test
    fun `withTimeoutOrNull` () {
        val start = System.currentTimeMillis()
        runBlocking {
            val result = withTimeoutOrNull(2000) {
                delay(1000)
                println("1: ${(System.currentTimeMillis() - start) / 1000F}s")
                "nicekkong"
            }
            println("result ====> ${result}")


            val result1 = withTimeoutOrNull(1000) {
                delay(2000)
                println("2: ${(System.currentTimeMillis() - start) / 1000F}s")
                "nicekkong1111"
            }
            println("result ====> ${result1}")

        }
    }

    @Test
    fun `time test`() {
        val start = System.currentTimeMillis()




        runBlocking {

//            complex1()

//            launch {
//                complex1()
//            }

            val a1 = async {
//                complex1()
                println("5:[${Thread.currentThread().name} ")
                1
            }

            val a2 = async {
//                complex2()
                println("6:[${Thread.currentThread().name} ")
                2
            }
            val awaitAll = awaitAll(a1, a2)
//            println("c1::: ${awaitAll.component1()}")
//            println("c2::: ${awaitAll.component2()}")
            complex1()
            println("4:[${Thread.currentThread().name} ")
        }

//        val aa1 = complex1()
//        val aa2 = complex2()
//
//        println("aa ===> $aa1 ::: $aa2")

        println("All takes ${(System.currentTimeMillis() - start) / 1000F}s")




    }

    suspend fun complex1():Int {
        val start: Long = System.currentTimeMillis()
        var sum = 0
        repeat(999999999) {
            sum += it
        }
        println("2:[${Thread.currentThread().name}")
        println("=> c1 takes ${(System.currentTimeMillis() - start) / 1000F}s")


        return sum
    }

    suspend fun complex2():Int {
//        complex1()

        val start = System.currentTimeMillis()
        var sum = 0
        repeat(899999999) {
            sum += it
        }
        println("3:[${Thread.currentThread().name}")
        println("c2 takes ${(System.currentTimeMillis() - start) / 1000F}s")
        return sum
    }


    @Test
    fun `suspend test`() {

        val start = System.currentTimeMillis()

        // 해당 코루틴 작업만 일시 중지되고, 쓰레드는 다른 작업 수행이 가능하다.
        runBlocking {
//            drawHead()
//            drawBody()
            launch {
                drawHead()
            }
            launch {
                drawBody()
            }
        }
        println("All takes ${(System.currentTimeMillis() - start) / 1000F}s")
    }


    @Test
    fun `coroutinescope`() {
        val time = measureTimeMillis {
            runBlocking {
                doDraw()
            }
        }
        println("total time::: ${time/1000F}s")
    }

    suspend fun doDraw() {
        // 해당 코루틴 작업만 일시 중지되고, 쓰레드는 다른 작업 수행이 가능하다.
        val time = measureTimeMillis {
            runBlocking{
                val one = async {
                    drawHead()
                }
                async{
                    drawBody()
                }
            }

        }
//        println("total Time::: ${time/1000F}s")
    }


    suspend fun drawHead() {
        println("[Init]Draw Head")
        delay(1)
        val start = System.currentTimeMillis()
        var sum = 0
        repeat(10) {
            sum += it
        }
        println("\t\t\t\t\tdrawHead takes ${(System.currentTimeMillis() - start) / 1000F}s")
        println("[DONE]Draw Head")

    }


     suspend fun drawBody() {

        println("[Init]Draw Body")
        val start = System.currentTimeMillis()
        delay(1)
        var sum = 0
        repeat(999999999) {
            sum += it
        }
        println("\t\t\t\t\tdrawBody takes ${(System.currentTimeMillis() - start) / 1000F}s")
        println("[DONE]Draw Body")
    }
}


