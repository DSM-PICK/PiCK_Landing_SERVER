package pick_landing_server.domain.user.admin.exception

import pick_landing_server.global.error.GlobalExceptionFilter
import pick_landing_server.global.error.exception.ErrorCode
import pick_landing_server.global.error.exception.PickException

object AdminNotFoundException : PickException(
    ErrorCode.ADMIN_NOT_FOUND
)
