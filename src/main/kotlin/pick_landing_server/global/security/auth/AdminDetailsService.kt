package pick_landing_server.global.security.auth


import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import pick_landing_server.domain.user.admin.domain.repository.AdminRepository

class AdminDetailsService(
    private val adminRepository: AdminRepository
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        return AdminDetails(username)
    }
}
