package pick_landing_server.global.security.jwt.exception

import pick_landing_server.global.error.exception.PickException
import pick_landing_server.global.error.exception.ErrorCode

object ExpiredTokenException : PickException (
    ErrorCode.EXPIRED_TOKEN
)

