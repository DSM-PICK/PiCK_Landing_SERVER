import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import jakarta.validation.ConstraintViolationException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import pick_landing_server.domain.application.domain.Major
import pick_landing_server.domain.application.presentation.dto.ApplicationRequest
import pick_landing_server.domain.application.service.ApplicationService

@SpringBootTest(classes = [ApplicationService::class])
class ApplicationTest {
    @MockBean
    private lateinit var applicationService: ApplicationService
    private val schoolNum = 1214
    private val phoneNum = "010-1234-1234"
    private val major = Major.BackEnd
    private val motivation = "안녕하세요"

    @Test
    fun applicationNoname() {
        val request = ApplicationRequest(
            name = "",
            schoolNum = schoolNum,
            phone = phoneNum,
            major = major,
            motivation = motivation
        )

        assertThrows(ConstraintViolationException::class.java) {
            applicationService.execute(request)
        }
    }
}
