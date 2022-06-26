package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.component.MyComponent
import com.nicekkong.kotlinboot.service.EmployeeService
import com.nicekkong.kotlinboot.service.SampleService
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import kotlin.collections.List


//class MyTests : FunSpec({
//    test("String length should return the length of the string") {
//        "sammy".length shouldBe 5
//        "".length shouldBe 0
//    }
//})
//
//

class MyShouldTests : ShouldSpec({
    should("return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})
//
//
@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MyRepoTest :ShouldSpec ({

    should("return length of String") {
        "Nicekkong".length shouldBe 9
        "Nicekkong~!!" shouldHaveLength 12
    }
})

//@DataJpaTest
//@Rollback(value = false)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)



@SpringBootTest
class SpringAutowiredConstructorTest @Autowired constructor(
    val employeeService: EmployeeService,
    val myComponent: MyComponent,
    val myCInfo: String
) : StringSpec({
    "should return the greeting provided by greeting service" {
        employeeService.countMapping() shouldBe 2

        println(myComponent.myInfo("adsf"))
        println(myCInfo)
    }
})
//
//


//
//


