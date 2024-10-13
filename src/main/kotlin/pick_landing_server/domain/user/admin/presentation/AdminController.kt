package pick_landing_server.domain.user.admin.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminSignupRequest
import pick_landing_server.domain.user.admin.service.AdminSignupService

@RestController
//@RequestMapping("/admin")
class AdminController(
    private val adminSignupService: AdminSignupService
) {
    @PostMapping("/signup")
    fun signup(@RequestBody request: AdminSignupRequest){
        println("asdf")
        adminSignupService.signup(request)
    }

    @GetMapping("/haha")
    fun haha():String = "haha"
}
