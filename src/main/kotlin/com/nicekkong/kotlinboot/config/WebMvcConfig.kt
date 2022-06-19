package com.nicekkong.kotlinboot.config

import com.nicekkong.kotlinboot.interceptor.RequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.config.annotation.*

@Configuration
class WebMvcConfig (
    val requestInterceptor: RequestInterceptor
    ):WebMvcConfigurer {

    companion object {
        val ONE_HOUR = 3600L
    }

//    override fun configureViewResolvers(registry: ViewResolverRegistry) {
//        registry.jsp("/WEB-INF", ".jsp")
//    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("*")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(ONE_HOUR)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        with(registry) {
            addResourceHandler("/**").addResourceLocations("/")
        }
    }

    @Bean
    fun characterEncodingFilter():CharacterEncodingFilter {
        return CharacterEncodingFilter().apply {
            encoding = "UTF-8"
            setForceEncoding(true)
        }
    }

}