package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.repository.MappingRepository
import com.nicekkong.kotlinboot.service.SampleService
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.*
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import kotlin.collections.List

/**
 * KoTest를 통한 테스트 방법
 * inteliJ를 사용하는 경우 KoTest plugin을 설치해야 test run & debug 가능하다.
 * https://kotest.io/
 * cf. https://techblog.woowahan.com/5825/
 *
 */
internal class SampleOfKoTest :AnnotationSpec() {

    @Test
    fun `문자열의 사이즈`() {
        "hello".length shouldBe 5
    }
}


internal class BehaviorSpecTest : BehaviorSpec({

    given("Input name") {
        val name = "nicekkong"
        `when`("${name} length ==>") {
            val len = name.length
            then("should be 9") {
                len shouldBe 9
            }
        }

        `when`("is lowerCase?") {
            var lowerName:String = ""
            name.forEach { n ->
                lowerName += n.lowercase()
            }
            val isLower = lowerName == name
            then("lower") {
                isLower shouldBe true
            }
        }
    }
})



internal class DescribeSpecTest:DescribeSpec({

    val name = "nicekkong"
    describe("My name is $name") {
        context("문자열의 길이는") {
            it("9를 반환한다.") {
                val len = name.length
                len shouldBe 9
            }
        }
        context("대소문자 여부는") {
            it("모두 소문자 이다.") {
                var lowerName:String = ""
                name.forEach { n ->
                    lowerName += n.lowercase()
                }
                val isLower = lowerName == name

                isLower shouldBe true
            }

        }

    }

})



@SpringBootTest
internal class SpringBeanTest @Autowired constructor(
    val sampleService: SampleService
): DescribeSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    val name = sampleService.getMyName()
    describe("My name is $name") {
        context("문자열의 길이는") {
            it("9를 반환한다.") {
                val len = name.length
                len shouldBe 9
            }
        }
    }
})
//{ override fun extensions(): List<Extension> = listOf(SpringExtension) }



@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class ShouldTest (
    val mappingRepository: MappingRepository
): ShouldSpec({
    // Kotest에서는 테스트 간 격리 레벨에 대해 디폴트로 SingleInstance를 설정하고 있으며 이 경우 Mocking 등의 이유로 테스트 간 충돌이 발생할 수 있음
    // 따라서 테스트간 완전한 격리를 위해서는 아래와 같이 IsolationMode를 InstancePerLeaf로 지정해 사용
    isolationMode = IsolationMode.InstancePerLeaf


    should("length of name") {
        val all = mappingRepository.findAll().size
        all shouldBe 2
    }


})
//{ override fun extensions(): List<Extension> = listOf(SpringExtension) }


@SpringBootTest
class EmpServiceTest: DescribeSpec() {
    // Archive All test classes
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    @Autowired
    private lateinit var sampleService: SampleService

    init {
        this.describe("desc....") {
            context("context....") {
                it("result....") {

                    val name = sampleService.getMyName()
                    name.length shouldBe 9
                }
            }
        }


        this.describe("desc2....") {
            context("context2....") {
                it("result2....") {

                    val name = sampleService.getMyName()
                    name.length shouldBe 9
                }
            }
        }
    }
}
