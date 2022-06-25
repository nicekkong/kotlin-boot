package com.nicekkong.kotlinboot.mybatis.mapper

import com.nicekkong.kotlinboot.entity.Project
import com.nicekkong.kotlinboot.mybatis.model.ProjectModel
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ProjectMapper {

    @Select("select id, title from tb_project")
    fun selectProject(): List<Project>?

    fun selectAllProject() : List<ProjectModel>?
}