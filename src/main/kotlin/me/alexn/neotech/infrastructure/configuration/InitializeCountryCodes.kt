package me.alexn.neotech.infrastructure.configuration

import me.alexn.neotech.application.domain.CountryCodeRepository
import me.alexn.neotech.application.scraper.Scraper
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration

private val logger = LoggerFactory.getLogger(InitializeCountryCodes::class.java)

@Configuration
class InitializeCountryCodes(
    private val scrapper: Scraper,
    private val repository: CountryCodeRepository
) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        try {
            scrapper.scrape()
                .let(repository::store)
        } catch (e: Exception) {
            logger.error("Failed to download and parse country codes, falling back to default values", e)
            repository.store(countryCodesFallback)
        }
    }
}
