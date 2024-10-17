import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import pick_landing_server.domain.user.admin.domain.Admin
import pick_landing_server.domain.user.admin.domain.repository.AdminRepository
import pick_landing_server.domain.user.admin.presentation.dto.request.AdminLoginRequest
import pick_landing_server.domain.user.admin.service.AdminLoginService
import pick_landing_server.global.security.jwt.JwtTokenProvider
import pick_landing_server.global.security.jwt.dto.TokenResponse

@SpringBootTest(classes = [AdminLoginServiceTest::class])
internal class AdminLoginServiceTest {

    private val adminRepository = Mockito.mock(AdminRepository::class.java)
    private val passwordEncoder = Mockito.mock(PasswordEncoder::class.java)
    private val jwtTokenProvider = Mockito.mock(JwtTokenProvider::class.java)
    private lateinit var adminLoginService: AdminLoginService

    private val testAdmin = Admin(
        id = 5555,
        accountId = "testAdmin",
        password = "testPassword"
    )

    private val tokenResponse = TokenResponse(
        accessToken = "asdfasdf",
        refreshToken = "asdfasdf"
    )

    @BeforeEach
    fun setUp() {
        adminLoginService = AdminLoginService(
            passwordEncoder = passwordEncoder,
            jwtTokenProvider = jwtTokenProvider,
            adminRepository = adminRepository
        )
    }

    @Test
    fun successfulLogin() {

         val testRequest = AdminLoginRequest(
            adminId = "testAdmin",
            password = "testPassword"
        )
        `when`(adminRepository.findByAccountId(testRequest.adminId)).thenReturn(testAdmin)
        `when`(passwordEncoder.matches(testRequest.password,testAdmin.password)).thenReturn(true)
        `when`(adminLoginService.login(testRequest)).thenReturn(tokenResponse)

        val testResponse = TokenResponse(accessToken = "asdfasdf", refreshToken = "asdfasdf")
        assertEquals(testResponse.refreshToken, adminLoginService.login(testRequest).refreshToken)
        assertEquals(testResponse.accessToken, adminLoginService.login(testRequest).accessToken)
    }

}

