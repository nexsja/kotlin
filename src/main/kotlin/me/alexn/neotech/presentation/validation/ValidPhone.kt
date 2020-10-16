package me.alexn.neotech.presentation.validation

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

const val PHONE_REGEXP = "^\\+\\d{6,}\$"

@Pattern(regexp = PHONE_REGEXP, message = "Invalid phone format: Phone number must start with a plus sign (+) and be at least 6 digits long")
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class ValidPhone(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
