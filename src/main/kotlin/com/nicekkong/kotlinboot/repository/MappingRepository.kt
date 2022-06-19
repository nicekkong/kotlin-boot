package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Mapping
import org.springframework.data.jpa.repository.JpaRepository

interface MappingRepository :JpaRepository<Mapping, Long> {
}