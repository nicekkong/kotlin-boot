package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.zample.dto.response.RandomUserResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.reactive.function.client.WebClient

@SpringBootTest
class RestTemplateTest @Autowired constructor(
    val restTemplate: RestTemplate
){

    @Test
    fun `test api`() {

        val forObject = restTemplate.getForObject(
            "https://randomuser.me/api/?inc=gender,name,nat",
            RandomUserResponse::class.java
        )

        forObject?.results?.forEach { r ->
            println(r.name)
        }

    }

    @Test
    fun `webClient test`() {

        val baseUrl = "https://randomuser.me"
        val wc = WebClient.builder()
            .baseUrl(baseUrl)
            .build()

        val result = wc.get().uri("/api?inc=gender,name,nat").retrieve()
            .toEntity(String::class.java).block()




    }

}