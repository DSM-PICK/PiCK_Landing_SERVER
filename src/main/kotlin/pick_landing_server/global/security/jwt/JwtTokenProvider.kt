package pick_landing_server.global.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import pick_landing_server.domain.auth.domain.RefreshToken
import pick_landing_server.domain.auth.domain.repository.RefreshTokenRepository
import pick_landing_server.global.security.auth.AdminDetailsService
import pick_landing_server.global.security.jwt.dto.TokenResponse
import pick_landing_server.global.security.jwt.exception.ExpiredTokenException
import pick_landing_server.global.security.jwt.exception.InvalidJwtException
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val adminDetailsService: AdminDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository

) {
    companion object{
        private const val ACCESS_KEY = "access_token"
        private const val REFRESH_KEY = "refresh_token"
    }
    public fun generateToken(userId:String):TokenResponse{
        val accessToken = generateToken(userId, ACCESS_KEY,jwtProperties.accessExp)
        val refreshToken = generateToken(userId, REFRESH_KEY,jwtProperties.refreshExp)

        refreshTokenRepository.save(RefreshToken(userId,refreshToken,jwtProperties.refreshExp))

        return TokenResponse(accessToken, refreshToken)
    }

    private fun generateToken(userId: String, tokenType: String, exp: Long):String {
        return Jwts.builder()
            .setSubject(userId)
            .setHeaderParam("typ",tokenType)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
           // .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(Date(System.currentTimeMillis()+exp*1000))
            .setIssuedAt(Date())
            .compact()

    }

    public fun reIssue(refreshToken: String): TokenResponse{
        if(!isRefreshToken(refreshToken)){
            throw InvalidJwtException
        }

        refreshTokenRepository.findByToken(refreshToken)
            ?.let { token ->
                val id = token.id

                val tokenResponse = generateToken(id)
                token.update(tokenResponse.refreshToken,jwtProperties.refreshExp)
                //return TokenResponse(tokenResponse.accessToken,tokenResponse.refreshToken)
                return tokenResponse
            } ?: throw InvalidJwtException
    }

    private fun isRefreshToken(token: String): Boolean{
        return REFRESH_KEY == getJws(token!!).header["typ"].toString()
    }

    public fun validToken(token: String): Boolean{
        try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
            return true
        }catch (e: Exception){
            return false
        }
    }

    public fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(jwtProperties.header)?.also {
            if(it.startsWith(jwtProperties.prefix)){
                return it.substring((jwtProperties.prefix.length))
            }
        }

    public fun authentication(token: String): Authentication?{
        val body: Claims = getJws(token).body
        val userDetails: UserDetails = getDetails(body)
        return UsernamePasswordAuthenticationToken(userDetails,"",userDetails.authorities)
    }

    private fun getClaimsToken(token: String): Claims{
        return Jwts.parser()
            .setSigningKey(jwtProperties.secretKey)
            .parseClaimsJws(token)
            .body
    }

    private fun getJws(token: String): Jws<Claims>{
        try {
            return Jwts.parser().setSigningKey(jwtProperties.secretKey).parseClaimsJws(token)
        } catch (e: ExpiredTokenException){
            throw ExpiredTokenException
        }catch (e: Exception){
            throw InvalidJwtException
        }
    }

    private fun getDetails(body:Claims):UserDetails{
        return adminDetailsService.loadUserByUsername(body.subject)
    }
}
