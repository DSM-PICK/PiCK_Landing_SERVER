package pick_landing_server.global.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import pick_landing_server.global.error.exception.PickException

@RestControllerAdvice
class GlobalExceptionHandler() {
    @ExceptionHandler(PickException::class)
    fun handlingPickException(e: PickException): ResponseEntity<ErrorReponse>{
        val code = e.errorCode
        return ResponseEntity(
            ErrorReponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }
}
