package pick_landing_server.domain.user.admin.presentation.dto.request

data class AdminSignupRequest(
    val adminId: String,
    val password: String
)
