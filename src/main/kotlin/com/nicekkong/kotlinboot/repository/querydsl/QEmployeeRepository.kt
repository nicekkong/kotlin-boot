package com.nicekkong.kotlinboot.repository.querydsl

import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.entity.QEmployee.employee
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