package pick_landing_server.domain.user.admin.presentation

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminSignupRequest
import pick_landing_server.domain.user.admin.service.AdminLoginService
import pick_landing_server.domain.user.admin.service.AdminSignupService
import pick_landing_server.global.security.jwt.JwtProperties
import pick_landing_server.global.security.jwt.JwtTokenProvider
import pick_landing_server.global.security.jwt.dto.TokenResponse

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminSignupService: AdminSignupService,
    private val adminLoginService: AdminLoginService,
    private val jwtTokenProvider: JwtProperties
) {
    @PostMapping("/signup")
    fun signup(@RequestBody request: AdminSignupRequest){
        adminSignupService.signup(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: AdminSignupRequest): TokenResponse {
        return adminLoginService.login(request)
    }
}
