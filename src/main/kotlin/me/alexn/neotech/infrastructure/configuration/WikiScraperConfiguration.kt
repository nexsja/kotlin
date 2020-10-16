package me.alexn.neotech.infrastructure.configuration

import me.alexn.neotech.application.scraper.Scraper
import me.alexn.neotech.application.scraper.WikiScraper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WikiScraperConfiguration(
    @Value("\${scrapper.url}")
    private val wikiUrl: String
) {
    @Bean
    fun wikiScrapper(): Scraper {
        return WikiScraper(wikiUrl)
    }
}
