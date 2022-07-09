package com.nicekkong.kotlinboot.zample.repository

import com.nicekkong.kotlinboot.zample.entity.Mapping
import org.springframework.data.jpa.repository.JpaRepository

interface MappingRepository :JpaRepository<Mapping, Long> {
}