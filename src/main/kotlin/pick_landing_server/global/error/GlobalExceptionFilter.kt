package pick_landing_server.global.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import pick_landing_server.global.error.exception.PickException
import java.io.IOException
import kotlin.jvm.Throws

class GlobalExceptionFilter (
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try{
            filterChain.doFilter(request, response)
        } catch (e: PickException){
            val errorCode = e.errorCode
            val errorResponse = ErrorReponse(errorCode.status, errorCode.message)
            writeErrorResponse(response, errorCode.status, errorResponse)
        }
    }

    @Throws(IOException::class)
    private fun writeErrorResponse(
        response: HttpServletResponse,
        status: Int,
        errorResponse:
            ErrorReponse
    ){
        response.status = status
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        objectMapper.writeValue(response.writer, errorResponse)
    }
}
