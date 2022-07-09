package com.nicekkong.kotlinboot.zample.repository.querydsl

import com.nicekkong.kotlinboot.zample.entity.Employee
import com.nicekkong.kotlinboot.zample.entity.QEmployee.employee
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QEmployeeRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun findAll() : List<Employee> {
        return queryFactory.selectFrom(employee).fetch()
    }
}