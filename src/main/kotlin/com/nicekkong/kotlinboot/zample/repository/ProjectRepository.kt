package com.nicekkong.kotlinboot.zample.repository

import com.nicekkong.kotlinboot.zample.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository:JpaRepository<Project, Long> {

    fun findByTitleContains(title:String):Project?
}