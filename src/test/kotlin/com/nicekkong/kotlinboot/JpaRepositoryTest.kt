package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.zample.entity.Department
import com.nicekkong.kotlinboot.zample.entity.Employee
import com.nicekkong.kotlinboot.zample.entity.Mapping
import com.nicekkong.kotlinboot.zample.entity.Project
import com.nicekkong.kotlinboot.zample.repository.DepartmentRepository
import com.nicekkong.kotlinboot.zample.repository.EmployeeRepository
import com.nicekkong.kotlinboot.zample.repository.MappingRepository
import com.nicekkong.kotlinboot.zample.repository.ProjectRepository
import com.nicekkong.kotlinboot.zample.repository.querydsl.QEmployeeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // TestUnit의 기본 DB인 H2가 아닌 어플리케이션의 DB 접속 정보를 활용한다.
@Rollback(value = false)
class JpaRepositoryTest{

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @Autowired
    private lateinit var mappingRepository: MappingRepository

//    @Autowired
//    lateinit var projectMapper:ProjectMapper

    @Test
    @Rollback(value = false)
    fun `project insert`() {

        val project = Project(
            id = null,
            title = "Title1122222211",
        )
        projectRepository.save(project)
    }

    @Test
    @Rollback(value = false)
    fun `test mapping entiry`() {

        val mapping = Mapping(id=null, senderId = 8888)
        mappingRepository.save(mapping)


    }

    @Test
    fun `select mapping`() {

        val id = mappingRepository.findById(1)

        val all = mappingRepository.findAll()

        val senderId = mappingRepository.findByIdOrNull(1)?.senderId ?:"___"


        println("senderId : $senderId")
    }


    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var departmentRepository: DepartmentRepository

    @Test
    @Rollback(value = false)
    fun `test join`() {

        val dept = Department().apply {
            deptName = "IT dev team5"
        }

        val emp  = Employee().apply{
            name = "nick5"
            job = "developer"
        }
        dept.addEmployee(emp)
        val id = departmentRepository.save(dept).id

        println("id ===> $id")
    }
}