package pick_landing_server.domain.user.admin.service

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pick_landing_server.domain.user.admin.domain.Admin
import pick_landing_server.domain.user.admin.domain.repository.AdminRepository
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminSignupRequest

@Service
class AdminSignupService (
    private val adminRepository: AdminRepository,
    //private val passwordEncoder: PasswordEncoder
){
    @Transactional
    public fun signup(request: AdminSignupRequest){
        adminRepository.save(
            Admin(
                accountId = request.adminId,
                password = request.password
            )
        )
    }
}
