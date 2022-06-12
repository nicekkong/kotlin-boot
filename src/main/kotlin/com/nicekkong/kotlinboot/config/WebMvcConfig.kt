package com.nicekkong.kotlinboot.config

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig:WebMvcConfigurer, WebMvcRegistrations {
    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.jsp("/WEB-INF", ".jsp")

    }

    override fun addInterceptors(registry: InterceptorRegistry) {


        super.addInterceptors(registry)
    }
}