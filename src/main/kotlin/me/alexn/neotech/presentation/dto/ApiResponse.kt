package me.alexn.neotech.presentation.dto

data class ApiResponse<T>(val message: String, val payload: T)

fun <T> ok(payload: T): ApiResponse<T> {
    return ApiResponse("OK", payload)
}

fun <T> error(payload: T): ApiResponse<T> {
    return ApiResponse("ERROR", payload)
}
