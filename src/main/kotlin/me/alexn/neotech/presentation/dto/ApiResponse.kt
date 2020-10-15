package me.alexn.neotech.presentation.dto

data class ApiResponse<T>(val status: String, val payload: T)

fun <T : Any> T.toApiResponse() = ApiResponse("OK", this)
fun <T : Any> T.toApiErrorResponse() = ApiResponse("ERROR", this)