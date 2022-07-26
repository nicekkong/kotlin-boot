package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.zample.dto.response.RandomUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDateTime
import java.util.*

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


    fun getNames():String {

        val start = System.currentTimeMillis()
        val names = runBlocking {

//            val n1 = async(Dispatchers.IO) {
            val n1 = async {
//                getApi1()
                getApi1WebClient()
            }

//            val n2 = async(Dispatchers.IO) {
            val n2 = async {
//                getApi2()
                getApi2WebClient()
            }
            val ns = awaitAll(n1, n2)
            ns.component1().block()
            ns.component1().block()
            "asdf"
        }

        println("Total End~!!!! ${(System.currentTimeMillis() - start)/1000F}s")
        return names
    }

    suspend fun getApi1WebClient(): Mono<ResponseEntity<RandomUserResponse>> {
        val start = System.currentTimeMillis()
        println("Api1 Start~!!!!")
        val baseUrl = "https://randomuser.me"
        val wc = WebClient.builder()
            .baseUrl(baseUrl)
            .build()

        val result = wc.get().uri("/api?inc=gender,name,nat").retrieve()
            .toEntity(RandomUserResponse::class.java)
        println("Api1 End~!!!! ${(System.currentTimeMillis() - start)/1000F}s")
        return result
    }

    suspend fun getApi2WebClient(): Mono<ResponseEntity<RandomUserResponse>> {
        val start = System.currentTimeMillis()
        println("Api2 Start~!!!!")
        val baseUrl = "https://randomuser.me"
        val wc = WebClient.builder()
            .baseUrl(baseUrl)
            .build()

        val result = wc.get().uri("/api?inc=gender,name,nat").retrieve()
            .toEntity(RandomUserResponse::class.java)
        println("Api2 End~!!!! ${(System.currentTimeMillis() - start)/1000F}s")
        return result
    }


    suspend fun getApi1(): String {
        val start = System.currentTimeMillis()
        println("Api1 Start~!!!!")
        val forObject = restTemplate.getForObject(
            "https://randomuser.me/api/?inc=gender,name,nat",
            RandomUserResponse::class.java
        )
        println("Api1 End~!!!! ${(System.currentTimeMillis() - start)/1000F}s")
        return forObject!!.results[0].name.first
    }


    suspend fun getApi2(): String {
        val start = System.currentTimeMillis()
        println("Api2 Start~!!!!")
        val forObject = restTemplate.getForObject(
            "https://randomuser.me/api/?inc=gender,name,nat",
            RandomUserResponse::class.java
        )
        println("Api2 End~!!!! ${(System.currentTimeMillis() - start)/1000F}s")
        return forObject!!.results[0].name.first
    }
}