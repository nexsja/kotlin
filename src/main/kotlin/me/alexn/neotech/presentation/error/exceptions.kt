package me.alexn.neotech.presentation.error

import org.springframework.http.HttpStatus

open class AppException(val httpStatus: HttpStatus, message: String?) : RuntimeException(message)

class NotFoundException : AppException(HttpStatus.NOT_FOUND, "Record not found")
