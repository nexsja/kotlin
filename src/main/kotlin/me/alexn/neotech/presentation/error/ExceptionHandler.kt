package me.alexn.neotech.presentation.error

import me.alexn.neotech.presentation.dto.ApiResponse
import me.alexn.neotech.presentation.dto.toApiErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

private val logger = LoggerFactory.getLogger(ExceptionHandler::class.java)

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(AppException::class)
    fun onNotFoundException(e: AppException): ResponseEntity<ApiResponse<AppError>> =
        ResponseEntity.status(e.httpStatus).body(
            e.toAppError().toApiErrorResponse()
        )

    @ExceptionHandler(ConstraintViolationException::class)
    fun onConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ApiResponse<AppError>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            e.toAppError().toApiErrorResponse()
        )

    @ExceptionHandler
    fun onInternalServerError(e: Throwable): ResponseEntity<ApiResponse<AppError>> {
        logger.error("Internal Server Error: " + e.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            e.toAppError().toApiErrorResponse()
        )
    }
}
