package pick_landing_server.domain.user.admin.exception

import pick_landing_server.global.error.exception.ErrorCode
import pick_landing_server.global.error.exception.PickException

object AlreadyExistAdmin :PickException(
    ErrorCode.ALREADY_EXIST_ADMIN
)
