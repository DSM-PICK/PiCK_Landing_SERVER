package pick_landing_server.domain.application.service

import org.springframework.stereotype.Service
import pick_landing_server.domain.application.domain.Application
import pick_landing_server.domain.application.domain.repository.ApplicationRepository
import pick_landing_server.domain.application.presentation.dto.ApplicationRequest

@Service
@org.springframework.transaction.annotation.Transactional
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

