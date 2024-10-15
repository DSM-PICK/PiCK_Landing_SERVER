package pick_landing_server.domain.user.admin.presentation.dto.request

data class AdminLoginRequest(
    val adminId: String,
    val password: String
)
