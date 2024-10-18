package pick_landing_server.global.security.jwt.exception

import pick_landing_server.global.error.exception.ErrorCode
import pick_landing_server.global.error.exception.PickException

object InvalidJwtException :PickException(
    ErrorCode.INVALID_TOKEN,
)
