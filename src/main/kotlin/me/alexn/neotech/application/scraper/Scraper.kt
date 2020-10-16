package me.alexn.neotech.application.scraper

import me.alexn.neotech.application.domain.CountryCode

interface Scraper {
    fun scrape(): List<CountryCode>
}
