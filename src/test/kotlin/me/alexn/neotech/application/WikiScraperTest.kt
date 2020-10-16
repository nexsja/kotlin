package me.alexn.neotech.application

import me.alexn.neotech.IntegrationTest
import me.alexn.neotech.application.scraper.Scraper
import me.alexn.neotech.infrastructure.configuration.countryCodesFallback
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class WikiScraperTest : IntegrationTest() {

    @Autowired
    private lateinit var scraper: Scraper

    @Test
    fun `fetching countries should contain all parsed country codes`() {
        val list = scraper.scrape()

        assertThat(list).isEqualTo(countryCodesFallback)
    }
}