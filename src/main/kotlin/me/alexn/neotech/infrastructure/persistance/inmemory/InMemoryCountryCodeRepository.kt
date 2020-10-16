package me.alexn.neotech.infrastructure.persistance.inmemory

import me.alexn.neotech.application.domain.CountryCode
import me.alexn.neotech.application.domain.CountryCodeRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryCountryCodeRepository : CountryCodeRepository {
    private var storage: List<CountryCode> = emptyList()

    override fun store(countryCodes: List<CountryCode>) {
        synchronized(this) {
            storage = countryCodes
        }
    }

    override fun findByPhone(phone: String): CountryCode? {
        return storage.find { c -> phone.startsWith(c.code) }
    }
}
