package com.nicekkong.kotlinboot

import com.nicekkong.kotlinboot.dto.response.EmployeeDto
import com.nicekkong.kotlinboot.dto.response.TeamDto
import com.nicekkong.kotlinboot.service.MemberService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MockKtest {

    @MockK
    lateinit var mockMemberService:MemberService

    @Test
    fun testMockK() {

        // Stub
        val mock = mockk<MemberService>(relaxed = true) // relaxed = true : mock 하지 않은 case에 대하여 Error을 발생 시키지 않는다.



        every { mock.getTotalMember() } returns 100
        every {mock.getMemberInfo(any())} returns EmployeeDto(id = 1, name="nicekkong")
        every {mock.getMemberInfo(1)} returns EmployeeDto(id = 1, name="nicekkong")
        every {mock.getMemberInfo(2)} throws IllegalArgumentException()

//        verify (exactly = 1) {mock.getTotalMember() }
//
//        verify (timeout = 10L) {mock.getTotalMember() }


        mock.getTotalMember() shouldBe 100

        mock.getMemberInfo(100)?.name shouldBe "nicekkong"
    }

    @Test
    fun `test returnsMay`() {

        every { mockMemberService.getTotalMember()} returnsMany listOf(100, 200, 300)
//        every { mockMemberService.getTotalMember()} returns 100 andThen 200 andThen 300

        val t1 = mockMemberService.getTotalMember()
        t1 shouldBe 100

        val t2 = mockMemberService.getTotalMember()
        t2 shouldBe 200

        val t3 = mockMemberService.getTotalMember()
        t3 shouldBe 300

        every{ mockMemberService.getMemberInfo(less(1L))} throws IllegalArgumentException()
//        every{ mockMemberService.getMemberInfo(100L)} answers {arg(0) * 2}
    }

    @Test
    fun `verify test`() {
        val mockService: MemberService = mockk(relaxed = true)
        every { mockService.getTotalMember() } returns 500

        val totalMember = mockService.getTotalMember()
        totalMember shouldBe 500

        verify { mockService.getTotalMember() } // 해당 메서드 호출 여부 검증
        verify(exactly = 1,) {mockService.getTotalMember()} // 해당 메서드가 exactly 횟수 만큼 호출
    }

    @Test
    fun `spyk test`() {
        // 전체 속성 중, 테스트하려는 속성만 설정하여 spy mock을 생성할 수 있다.
        val mockEmp = mockk<TeamDto> {
            every { name } returns "nicekkong"
        }
        spyk(mockEmp).getMyName() shouldBe "nicekkong님"
        mockEmp.getMyName() shouldBe "nicekkong님"

//         해당 객체에 대하여 전체 값을 모두 셋팅하지 않아도 정상적으로 객체(Mock)을 생성한다.
//        val spyTeam = spyk(TeamDto(name = "닉과르")) // spyk() 에는 유효한 Entity가 들어와야 하는데, id가 누락이 되어 있으므로 초기화 오류 발생
//        spyTeam.getMyName() shouldBe "닉과르님"

    }

    @Test
    fun `test when`() {
        every{
            mockMemberService.getMemberInfo(range(0L, 100L))
        } returnsMany  listOf(
            EmployeeDto(id=1, name="nicekkong1"),
            EmployeeDto(id=2, name="nicekkong2"),
            EmployeeDto(id=3, name="nicekkong3"))

        mockMemberService.getMemberInfo(100L)?.name shouldBe "nicekkong1"
        mockMemberService.getMemberInfo(100L)?.name shouldBe "nicekkong2"
        mockMemberService.getMemberInfo(100L)?.name shouldBe "nicekkong3"

    }
}