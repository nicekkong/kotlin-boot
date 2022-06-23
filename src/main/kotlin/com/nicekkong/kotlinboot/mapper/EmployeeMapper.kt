package com.nicekkong.kotlinboot.mapper

import com.nicekkong.kotlinboot.dto.common.EmpDto
import com.nicekkong.kotlinboot.entity.Employee
import org.mapstruct.*
import org.springframework.stereotype.Component

// mapStruct 가이드: https://meetup.toast.com/posts/213 , https://taylor-kang.tistory.com/20
// 속성 메뉴얼: https://mapstruct.org/documentation/stable/reference/html/#basic-mappings
@Mapper
// @Mapper(componentModel = "spring") : componentModel="spring"을 선언해야 Spring Singleton bean으로 생성한다., gradle arg로 선언하했기 때문에 작성 생략가능
interface EmployeeMapper {
    @Mappings(
        Mapping(source = "job", target = "workJob"),
        Mapping(source = "dept.deptName", target = "department")
    )
    fun toEmpDto(emp:Employee): EmpDto
}
