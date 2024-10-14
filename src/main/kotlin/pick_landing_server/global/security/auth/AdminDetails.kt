package pick_landing_server.global.security.auth

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


class AdminDetails (
    private val adminName: String,
): UserDetails{

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf<SimpleGrantedAuthority>(SimpleGrantedAuthority("Admin"))
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return adminName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
