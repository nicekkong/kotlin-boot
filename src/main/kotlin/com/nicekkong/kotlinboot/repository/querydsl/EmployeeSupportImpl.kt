package com.nicekkong.kotlinboot.repository.querydsl

import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.entity.QEmployee.employee
import com.querydsl.jpa.impl.JPAQueryFactory

class EmployeeSupportImpl (
    private val query:JPAQueryFactory,
        ) : CustomQuerydslRepositorySupport(Employee::class.java), EmployeeSupport {

    override fun getEmp(): List<Employee> {
        return query.selectFrom(employee).fetch()
    }

}