package pick_landing_server.domain

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pick_landing_server.domain.auth.domain.repository.RefreshTokenRepository
import pick_landing_server.global.security.jwt.exception.InvalidJwtException

@RestController
@RequestMapping("/test")
class TestController(
    private val refreshTokenRepository: RefreshTokenRepository
) {
    @GetMapping("/haha/{refresh}")
    fun haha(@PathVariable refresh:String): String {
       return refreshTokenRepository.findByToken(refresh)?.token ?: throw InvalidJwtException
   }
}
