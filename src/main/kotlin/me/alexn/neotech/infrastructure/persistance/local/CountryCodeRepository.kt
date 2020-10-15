package me.alexn.neotech.infrastructure.persistance.local

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import org.springframework.stereotype.Component

@Component
class CountryCodeRepository : CountryCodeRepository {
    private var storage: List<CountryCode> = emptyList()

    override fun store(entities: List<CountryCode>) {
        storage = entities
    }

    override fun findCountryByPhone(number: String): CountryCode? {
        return storage.find { c -> number.startsWith(c.code) }
    }
}
