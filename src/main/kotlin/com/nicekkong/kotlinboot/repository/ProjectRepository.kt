package com.nicekkong.kotlinboot.repository

import com.nicekkong.kotlinboot.entity.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository:JpaRepository<Project, Long> {

    fun findByTitleContains(title:String):Project?
}