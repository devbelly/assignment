package co.getaim.exception

import co.getaim.common.utils.logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    private val log = logger()

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, e: Exception): ResponseEntity<ExceptionResponse> {
        log.error("예상하지 못한 예외가 발생했습니다. URI: ${request.requestURI}, ${e.message}", e)
        return ResponseEntity.internalServerError().body(
            ExceptionResponse(
                code = "G03",
                message = "서버가 응답할 수 없습니다.",
            ),
        )
    }
}