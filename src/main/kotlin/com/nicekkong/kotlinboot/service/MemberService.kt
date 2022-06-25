package com.nicekkong.kotlinboot.service

import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import org.springframework.stereotype.Service


// Interface for MockK test sample code
interface MemberService {

    fun getMemberList(team:String): List<EmployeeDto>?

    fun getMemberInfo(id:Long): EmployeeDto?

    fun getTotalMember(): Int

}