package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.entity.Mapping
import com.nicekkong.kotlinboot.entity.Project
import com.nicekkong.kotlinboot.repository.MappingRepository
import com.nicekkong.kotlinboot.repository.ProjectRepository
import org.assertj.core.api.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // TestUnit의 기본 DB인 H2가 아닌 어플리케이션의 DB 접속 정보를 활용한다.
@Rollback(value = false)
class RepositoryTest{

    @Autowired
    private lateinit var projectRepository: ProjectRepository

    @Autowired
    private lateinit var mappingRepository: MappingRepository

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

}