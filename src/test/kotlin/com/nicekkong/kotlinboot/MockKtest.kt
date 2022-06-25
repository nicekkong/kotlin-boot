package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.service.MemberService
import io.kotest.matchers.ints.exactly
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`

@ExtendWith(MockKExtension::class)
class MockKtest {

    @Test
    fun testMockK() {
        // Stub
        val mock = mockk<MemberService>()
        every { mock.getTotalMember() } returns 100
        every {mock.getMemberInfo(any())} returns EmployeeDto(id = 1, name="nicekkong")

        `when` (mock.getTotalMember()).thenReturn(777)
//        `when` (mock.getMemberInfo(any())).thenReturn(EmployeeDto(id = 1, name="nicekkong"))

//        verify (exactly = 1) {mock.getTotalMember() }
//
//        verify (timeout = 10L) {mock.getTotalMember() }

    }
}