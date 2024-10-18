package pick_landing_server.domain.application.presentation

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import pick_landing_server.domain.application.presentation.dto.ApplicationRequest
import pick_landing_server.domain.application.service.ApplicationService

@RestController
@RequestMapping("/application")
class ApplicationController (
    private val applicationService: ApplicationService
){
    @PostMapping
    fun application(@Valid @RequestBody applicationRequest: ApplicationRequest){
        applicationService.execute(applicationRequest)
    }
}
