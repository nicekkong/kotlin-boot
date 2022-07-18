package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.zample.dto.response.EmployeeDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
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

        var emp1:EmployeeDto
        var emp2:EmployeeDto

        runBlocking {
            val seoul = async {
                callApi("seoul")
            }

            val busan = async {
                callApi("busan")
            }

//            val locations = awaitAll(seoul, busan)

            emp1 = seoul.await()
            emp2 = busan.await()

//            emp1:EmployeeDto = locations.component1()
//            emp2:EmployeeDto = locations.component2()
        }
        return emp1.name!!
    }

    suspend fun callApi(location:String):EmployeeDto {
        return restTemplate.getForObject<EmployeeDto>("http://www.naver.com")
    }



    suspend fun getLocation():String = "Seoul"
    suspend fun getWeather():String = "Good"
    suspend fun getDate():LocalDateTime = LocalDateTime.now()
}