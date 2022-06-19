package com.nicekkong.kotlinboot.service

import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.ServerResponse.async
import java.time.LocalDateTime
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

@Service
class CoroutineService {

//    suspend fun getLocalAndWeather() =  coroutineContext({
//            val date = getDate()
//            val location = async { getLocation() }
//            val weather = async { getWeather() }
//
//            "$location /// $weather"
//        })



    suspend fun getLocation():String = "Seoul"
    suspend fun getWeather():String = "Good"
    suspend fun getDate():LocalDateTime = LocalDateTime.now()
}