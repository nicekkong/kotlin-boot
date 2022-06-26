package com.nicekkong.kotlinboot.config

import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class RestTemplateConfig{

    @Bean
    fun resTemplate(): RestTemplate {

        val httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(3000)
            .setMaxConnPerRoute(3000)
            .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        factory.setConnectTimeout(Duration.ofSeconds(3).toMillis().toInt())
        factory.setReadTimeout(Duration.ofSeconds(10).toMillis().toInt())
        return RestTemplateBuilder()
            .requestFactory { factory }
            .build()
    }
}