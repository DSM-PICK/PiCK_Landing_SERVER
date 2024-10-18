package pickdomain.pick_landing_server

import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import pick_landing_server.domain.application.domain.Application
import pick_landing_server.domain.application.domain.Major
import pick_landing_server.domain.application.domain.repository.ApplicationRepository
import pick_landing_server.domain.application.presentation.dto.ApplicationRequest
import pick_landing_server.domain.application.service.ApplicationService
import kotlin.test.Test

@SpringBootTest(classes = [ApplicationService::class])
class ApplicationTest {
    @MockBean
    private lateinit var applicationRepository: ApplicationRepository
    @MockBean
    private lateinit var applicationService: ApplicationService
    private val id: Long = 0
    private val name = "임한성"
    private val schoolNum = 1214
    private val phoneNum = "010-1234-1234"
    private val major = Major.BackEnd
    private val motivation = "안녕하세요"

    @Test
    fun application() {
        val request = ApplicationRequest(
            name,
            schoolNum,
            phoneNum,
            major,
            motivation
        )
        applicationService.execute(request)
    }

    @Test
    fun saveApplication() {
        val a = (
            Application(
                id = id,
                name = name,
                schoolNum = schoolNum,
                phone = phoneNum,
                major = major,
                motivation = motivation
            )
        )
        `when`(applicationRepository.save(a)).thenReturn(a)

        val savedApplication = applicationRepository.save(a)

        assertNotNull(savedApplication)
        println("Saved application: $savedApplication")
    }
}
