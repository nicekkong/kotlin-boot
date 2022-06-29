package com.nicekkong.kotlinboot.config

import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration
class RestTemplateConfig{

    @Value("\${restTemplate.max.conn:5}")
    private lateinit var maxConn:String

    @Value("\${restTemplate.max.perRoute:5}")
    private lateinit var maxPerRoute:String

    @Value("\${restTemplate.connectTimeout:3}")
    private lateinit var connectTimeout:String

    @Value("\${restTemplate.readTimeout:5}")
    private lateinit var readTimeout:String

    @Bean
    fun resTemplate(): RestTemplate {

        val httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(maxConn.toInt())
            .setMaxConnPerRoute(maxPerRoute.toInt())
            .build()

        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        factory.setConnectTimeout(Duration.ofSeconds(connectTimeout.toLong()).toMillis().toInt())
        factory.setReadTimeout(Duration.ofSeconds(readTimeout.toLong()).toMillis().toInt())
        return RestTemplateBuilder()
            .requestFactory { factory }
            .build()
    }
}
