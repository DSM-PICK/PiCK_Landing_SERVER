package pick_landing_server.domain.user.admin.service

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pick_landing_server.domain.user.admin.domain.repository.AdminRepository
import pick_landing_server.domain.user.admin.exception.AdminNotFoundException
import pick_landing_server.domain.user.admin.exception.PasswordMissMatchException
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminSignupRequest
import pick_landing_server.global.security.jwt.JwtTokenProvider
import pick_landing_server.global.security.jwt.dto.TokenResponse

@Service
class AdminLoginService (
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val adminRepository: AdminRepository
){
    @Transactional
    fun login(request: AdminSignupRequest):TokenResponse{
        val admin = adminRepository.findByAccountId(request.adminId) ?: throw AdminNotFoundException

        if(passwordEncoder.matches(request.password,admin.password)){
            return jwtTokenProvider.generateToken(admin.accountId)
        }else{
            throw PasswordMissMatchException
        }
    }
}
