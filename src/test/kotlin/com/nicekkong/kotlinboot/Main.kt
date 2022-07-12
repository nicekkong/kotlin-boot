package com.nicekkong.kotlinboot

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val start = System.currentTimeMillis()
    println("111: ${(System.currentTimeMillis() - start)/1000F}s")
    doWorld()
    println("222: ${(System.currentTimeMillis() - start)/1000F}s")
    println("Done")
}

// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}