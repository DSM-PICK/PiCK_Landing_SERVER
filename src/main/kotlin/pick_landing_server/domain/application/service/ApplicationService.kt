package pick_landing_server.domain.application.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pick_landing_server.domain.application.domain.Application
import pick_landing_server.domain.application.domain.repository.ApplicationRepository
import pick_landing_server.domain.application.presentation.dto.ApplicationRequest

@Service
@Transactional
class ApplicationService (
    private val applicationRepository: ApplicationRepository
){
    fun execute(request: ApplicationRequest) {
        val application = Application(
            name = request.name,
            schoolNum = request.schoolNum,
            phone = request.phone,
            major = request.major,
            motivation = request.motivation
        )
        applicationRepository.save(application)
    }
}

