package pickdomain.pick_landing_server.domain.application.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import pick_landing_server.domain.application.domain.Application
import pick_landing_server.domain.application.domain.Major
import pick_landing_server.domain.application.domain.repository.ApplicationRepository
import pick_landing_server.domain.application.presentation.dto.QueryApplicationResponse
import pick_landing_server.domain.application.service.QueryApplicationService
import java.time.LocalDateTime
import kotlin.test.assertEquals

@SpringBootTest(classes = [QueryApplicationService::class])
class ApplicationQueryServiceTest {

    @MockBean
    private lateinit var applicationRepository: ApplicationRepository
    @MockBean
    private lateinit var queryApplicationService: QueryApplicationService

    private val response:QueryApplicationResponse = QueryApplicationResponse(1,"강감찬",1407,"01012341234",Major.BackEnd,"제발픽가고싶어요제발제발",
    LocalDateTime.now())
    private val app: Application = Application(1,"강감찬",1407,"01012341234",Major.BackEnd,"제발픽가고싶어요제발제발", LocalDateTime.now())
    private val appList : List<QueryApplicationResponse> = listOf(response)

    @Test
    fun successfulQuery() {
        `when`(applicationRepository.save(app)).thenReturn(app)
        `when`(queryApplicationService.queryApplication()).thenReturn(appList)

        assertEquals(appList.get(0).name, app.name)
        assertEquals(appList.get(0).id, app.id)
    }

}
