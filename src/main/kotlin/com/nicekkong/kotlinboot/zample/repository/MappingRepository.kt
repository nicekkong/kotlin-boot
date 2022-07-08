package com.nicekkong.kotlinboot.zample.repository

import org.springframework.data.jpa.repository.JpaRepository

interface MappingRepository :JpaRepository<Mapping, Long> {
}