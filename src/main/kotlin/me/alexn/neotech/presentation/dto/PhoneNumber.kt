package me.alexn.neotech.presentation.dto

import me.alexn.neotech.domain.CountryCode

data class PhoneNumber(val number: String)

fun PhoneNumber.belongsTo(countryCode: CountryCode): Boolean {
    return this.number.startsWith(countryCode.code)
}