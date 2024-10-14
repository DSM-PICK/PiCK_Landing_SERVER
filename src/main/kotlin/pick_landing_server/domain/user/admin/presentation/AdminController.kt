package pick_landing_server.domain.user.admin.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminLoginRequest
import pick_landing_server.domain.user.admin.service.AdminLoginService
import pick_landing_server.global.security.jwt.dto.TokenResponse

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminLoginService: AdminLoginService,
) {
    @PostMapping("/login")
    fun login(@RequestBody request: AdminLoginRequest): TokenResponse {
        return adminLoginService.login(request)
    }
}
