package com.nicekkong.kotlinboot.repository.jdbcTemplate

import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class EmployeeJdbcRepository(
    val jdbcTemplate: JdbcTemplate
) {

    val mapper = RowMapper<EmployeeDto> { rs: ResultSet, rowNum: Int ->
        EmployeeDto(
            rs.getLong("id"),
            rs.getString("name"),
        )

    }

    fun findEmployeeById(id:Long): EmployeeDto? {
        val emp: EmployeeDto? = jdbcTemplate.queryForObject("select id, name from tb_employee where id = ?", mapper, id)
        return emp
    }
}