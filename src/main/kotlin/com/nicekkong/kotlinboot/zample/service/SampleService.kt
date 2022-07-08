package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.utils.DateUtils
import com.nicekkong.kotlinboot.zample.mybatis.mapper.ProjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SampleService(
    val projectMapper: ProjectMapper
) {


    fun getMyName() = "Nicekkong"


    fun getNowDate() = DateUtils.toStringYYYYMMDDHHMMSS(LocalDateTime.now())


    fun myBatis() {

        val selectAllProject = projectMapper.selectAllProject()
        val selectProject = projectMapper.selectProject()

        print(selectAllProject?.size?:0)

    }
}