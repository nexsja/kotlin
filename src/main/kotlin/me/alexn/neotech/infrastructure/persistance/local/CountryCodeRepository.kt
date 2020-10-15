package me.alexn.neotech.infrastructure.persistance.local

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import org.springframework.stereotype.Component

@Component
class CountryCodeRepository : CountryCodeRepository {
    private var storage: MutableList<CountryCode> = mutableListOf()

    override fun store(entity: CountryCode) {
        if (!storage.contains(entity)) {
            storage.add(entity)
        }
    }

    override fun store(entities: List<CountryCode>) {
        entities.forEach { store(it) }
    }

    override fun findCountryByPhone(number: String): CountryCode? {
        return storage.find { c -> number.startsWith(c.code) }
    }

}