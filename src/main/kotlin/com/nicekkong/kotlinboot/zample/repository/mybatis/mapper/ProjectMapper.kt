package com.nicekkong.kotlinboot.zample.repository.mybatis.mapper

import com.nicekkong.kotlinboot.zample.repository.mybatis.model.ProjectModel
import com.nicekkong.kotlinboot.zample.entity.Project
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ProjectMapper {

    @Select("select id, title from tb_project")
    fun selectProject(): List<Project>?

    fun selectAllProject() : List<ProjectModel>?
}