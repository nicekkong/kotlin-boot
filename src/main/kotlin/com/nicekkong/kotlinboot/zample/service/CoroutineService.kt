package com.nicekkong.kotlinboot.zample.service

import org.springframework.stereotype.Service
import java.time.LocalDateTime

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