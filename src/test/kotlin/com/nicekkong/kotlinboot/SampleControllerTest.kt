package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.zample.controller.IndexController
import com.nicekkong.kotlinboot.zample.service.EmployeeService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(IndexController::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class SampleControllerTest(val mockMvc: MockMvc) {

    @MockBean
    private lateinit var employeeService: EmployeeService

    @Test
    fun `화면 가져오기`() {
        given(employeeService.findById(1)).willReturn(null)

        mockMvc.get("/save/emp") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status{isOk()}
            view{ name("/")}
            model { attributeExists("emp001")}
        }
    }
}