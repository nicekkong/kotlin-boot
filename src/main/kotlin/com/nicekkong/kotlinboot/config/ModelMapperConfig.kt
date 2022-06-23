package com.nicekkong.kotlinboot.config

import com.nicekkong.kotlinboot.dto.response.MapResponse
import com.nicekkong.kotlinboot.entity.Mapping
import com.nicekkong.kotlinboot.entity.Project
import org.modelmapper.ModelMapper
import org.modelmapper.PropertyMap
import org.modelmapper.config.Configuration.AccessLevel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {

    @Bean
    fun modelMapper(): ModelMapper  =
        ModelMapper().apply {
            this.configuration.isFieldMatchingEnabled = true
            this.configuration.fieldAccessLevel = AccessLevel.PRIVATE
        }


//    fun mapModelMapper():ModelMapper {
//        val mapModelMapper = ModelMapper()
//        mapModelMapper.configuration.fieldAccessLevel = AccessLevel.PRIVATE
//        mapModelMapper.typeMap(Project::class.java, MapResponse::class.java).addMappings {
//            mapper -> mapper.map(Project::title, MapResponse::mapTitle)
//        }
//        return mapModelMapper
//
//    }



}

