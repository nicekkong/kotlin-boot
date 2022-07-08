package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.zample.mybatis.mapper.ProjectMapper
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

/**
 * MyBatis 전용 테스트
 * @BootStrapWith @MybatisTest & @SpringBootTest & @DataJpaTest)
 */
// @MybatisTest & @SpringBootTest & @DataJpaTest 는 각기 다른 @BootstrapWith 을 설정하기 때문에 함께 사용하면 안된다.
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 DB 연결, MyBatis 테스트에는 내부에 정의된 빈 sqlSession 객체가 아닌, 실제 설정된 DB값으로 테스트 한다.
class MyBatisTest {

    @Autowired
    lateinit var projectMapper: ProjectMapper

    @Test
    fun `test mybatis`() {

        val selectProject = projectMapper.selectProject()
        val selectAllProject = projectMapper.selectAllProject()

        selectProject?.size shouldBe 4
        selectAllProject?.size shouldBe 4


    }
}


//@SpringBootTest
//class SpringAutowiredConstructorTest @Autowired constructor(
//    val employeeService: EmployeeService
//) : StringSpec({
//    "should return the greeting provided by greeting service" {
//        employeeService.countMapping() shouldBe 2
//    }
//})