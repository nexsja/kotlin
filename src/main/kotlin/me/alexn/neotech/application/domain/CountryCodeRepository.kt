package me.alexn.neotech.application.domain

interface CountryCodeRepository {
    fun store(countryCodes: List<CountryCode>)

    fun findByPhone(phone: String): CountryCode?
}
