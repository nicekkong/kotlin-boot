package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.zample.dto.response.EmployeeDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.time.LocalDateTime

@Service
class CoroutineService(
    val restTemplate: RestTemplate
) {

//    suspend fun getLocalAndWeather() =  coroutineContext({
//            val date = getDate()
//            val location = async { getLocation() }
//            val weather = async { getWeather() }
//
//            "$location /// $weather"
//        })


    fun getWeatherByApi():String {

        runBlocking {
            val seoul = async {
                println("Seoul~!!!")
                callApi1("seoul")
            }

            val busan = async {
                println("Busan~!!!")
                callApi2("busan")
            }

//            val locations = awaitAll(seoul, busan)

            val emp1 = seoul.await()
            val emp2 = busan.await()

//            emp1:EmployeeDto = locations.component1()
//            emp2:EmployeeDto = locations.component2()
        }
        return "asdf"
    }

    suspend fun callApi1(location:String):String {
        println("$location  ::: callApi Call by ${Thread.currentThread().name}")
//        restTemplate.getForObject<String>("https://www.naver.com")
        
        val wc = WebClient.builder()
            .baseUrl("https://www.naver.com")
            .build()
        wc.get()
            .uri("/index")
            .retrieve()


        println("${location} is done")
        return location
    }

    suspend fun callApi2(location:String):String {
        println("$location ::: callApi Call by ${Thread.currentThread().name}")
//        restTemplate.getForObject<String>("https://www.naver.com")
        val wc = WebClient.builder()
            .baseUrl("https://www.naver.com")
            .build()
        wc.get()
            .uri("/index")
            .retrieve()


        println("${location} is done")
        return location
    }



    suspend fun getLocation():String = "Seoul"
    suspend fun getWeather():String = "Good"
    suspend fun getDate():LocalDateTime = LocalDateTime.now()
}