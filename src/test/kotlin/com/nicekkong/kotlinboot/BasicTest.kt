package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.component.MyComponent
import com.nicekkong.kotlinboot.config.MyConfiguration
import com.nicekkong.kotlinboot.repository.EmployeeQueryDslRepository
import com.nicekkong.kotlinboot.service.EmployeeService
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveLength
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback


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
    val myName: String,
    val myInfo: String,
    val myConfiguration: MyConfiguration,
    val myCInfo: String,
    val employeeQueryDslRepository: EmployeeQueryDslRepository
) : StringSpec({
    "should return the greeting provided by greeting service" {
        employeeService.countMapping() shouldBe 2

        println(myName)
        println(myInfo)
        println("myComponent.myInfo(\"컴포넌트\") ===> ${myComponent.myInfo("컴포넌트")}")

        println(myCInfo)

        println("myConfiguration.myCInfo('컨피규레이션') ===> ${myConfiguration.myCInfo("컨피규레이션")}")

        for (employee in employeeQueryDslRepository.getEmp()) {
            println("${employee.name} ::: ${employee.job}")
        }

    }
})
//
//


//
//


