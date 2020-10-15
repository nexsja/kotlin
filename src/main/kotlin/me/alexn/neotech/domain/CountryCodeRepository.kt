package me.alexn.neotech.domain

interface CountryCodeRepository {
    fun store(entity: CountryCode)

    fun store(entities: List<CountryCode>)

    fun findCountryByPhone(number: String): CountryCode?
}