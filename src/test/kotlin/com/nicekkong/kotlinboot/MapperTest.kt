package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.entity.Department
import com.nicekkong.kotlinboot.entity.Employee
import com.nicekkong.kotlinboot.mapper.EmployeeMapper
import com.nicekkong.kotlinboot.service.EmployeeService
import com.nicekkong.kotlinboot.service.SampleService
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.collections.List


@SpringBootTest
class MapperTest @Autowired constructor(
    val employeeMapper: EmployeeMapper
) : AnnotationSpec() {

    @Test
    fun `empDto Mapper Test`() {
        val employee = Employee(
            id = 77L,
            name = "nice",
            job = "S/W developer",
            dept = Department(id = 1, deptName = "개발팀")
        )

        val empDto = employeeMapper.toEmpDto(employee)

        empDto.id shouldBe 77L
        empDto.name shouldBe "nice"
        empDto.workJob shouldBe "S/W developer"
        empDto.department shouldBe "개발팀"
    }
}


@SpringBootTest
class EmpMapperTest (
    val employeeMapper: EmployeeMapper
        ): DescribeSpec() {
    // Archive All test classes
//    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        this.describe("employee entity를 생성해서") {
            val employee = Employee(
                id = 77L,
                name = "nice",
                job = "S/W developer",
                dept = Department(id = 1, deptName = "개발팀")
            )

            context("DTO mapper로 수행하면") {
                val empDto = employeeMapper.toEmpDto(employee)

                it("정상적으로 맵핑이 된다.") {
                    empDto.id shouldBe 77L
                    empDto.name shouldBe "nice"
                    empDto.workJob shouldBe "S/W developer"
                    empDto.department shouldBe "개발팀"
                }
            }
        }
    }
}