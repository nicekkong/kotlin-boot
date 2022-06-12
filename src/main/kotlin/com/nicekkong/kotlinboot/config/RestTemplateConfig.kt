package com.nicekkong.kotlinboot.config

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class RestTemplateConfig{

    @Bean
    fun restTemplate():RestTemplate {
        return RestTemplateBuilder().build()
    }
}