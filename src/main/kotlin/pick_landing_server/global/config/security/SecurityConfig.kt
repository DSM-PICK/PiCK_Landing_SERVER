package pick_landing_server.global.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import pick_landing_server.global.error.GlobalExceptionFilter
import pick_landing_server.global.security.jwt.JwtFilter
import pick_landing_server.global.security.jwt.JwtTokenProvider

@EnableWebSecurity
@Configuration
class SecurityConfig (
    private val objectMapper: ObjectMapper,
    private val jwtTokenProvider: JwtTokenProvider
){
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        http
            .csrf{it.disable()}
            .cors{it.disable()}
            .formLogin{it.disable()}
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("admin/signup","admin/login").permitAll()
                    .requestMatchers("/application/**").permitAll()
            }

        .addFilterBefore(JwtFilter(jwtTokenProvider),UsernamePasswordAuthenticationFilter::class.java)
        .addFilterBefore(GlobalExceptionFilter(objectMapper),JwtFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}
