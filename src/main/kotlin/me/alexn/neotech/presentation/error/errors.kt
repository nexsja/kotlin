package me.alexn.neotech.presentation.error

data class AppError(val statusText: String)

fun Throwable.toAppError(): AppError = AppError(message ?: "Internal Server Error")
