package com.nicekkong.kotlinboot.zample.service

import com.nicekkong.kotlinboot.zample.dto.response.EmployeeDto


// Interface for MockK test sample code
interface MemberService {

    fun getMemberList(team:String): List<EmployeeDto>?

    fun getMemberInfo(id:Long): EmployeeDto?

    fun getTotalMember(): Int

}