package pick_landing_server.domain.application.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pick_landing_server.domain.application.domain.repository.ApplicationRepository
import pick_landing_server.domain.application.presentation.dto.QueryApplicationResponse
import java.time.LocalDate

@Service
@Transactional
class QueryApplicationService (
    private val applicationRepository: ApplicationRepository
){
    fun queryApplication(): List<QueryApplicationResponse> {
        return applicationRepository.findAll()
            .map { app -> QueryApplicationResponse(
                name = app.name,
                schoolNum = app.schoolNum,
                phone = app.phone,
                major = app.major,
                motivation = app.motivation,
                date = app.date,
                id = app.id
            ) }.toList()
    }
}
