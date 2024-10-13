package pick_landing_server.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {

    PASSWORD_MISS_MATCH(401, "Password Miss Match"),
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    ADMIN_NOT_FOUND(404, "Admin Not Found"),
}
