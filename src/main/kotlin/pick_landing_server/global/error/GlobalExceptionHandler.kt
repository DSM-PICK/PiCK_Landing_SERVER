package pick_landing_server.global.error

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationException(e: MethodArgumentNotValidException): ResponseEntity<ErrorReponse> {
        return ResponseEntity(
            ErrorReponse(
                400,
                e.bindingResult.allErrors[0].defaultMessage
            ),
            HttpStatus.BAD_REQUEST
        )
    }
}
