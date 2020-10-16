package me.alexn.neotech.application.scraper

import me.alexn.neotech.application.domain.CountryCode
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

private val phoneExtensionRegexp = Regex("""\+([\d\s]+)""")
private val nameCleanupRegexp = Regex("""\[notes\s?\d+?\]""")

class WikiScraper(private val wikiUrl: String) : Scraper {

    override fun scrape(): List<CountryCode> =
        Jsoup.connect(wikiUrl)
            .get()
            .select("h2:contains(Alphabetical listing by country or region)")
            .next()
            .select("tbody > tr")
            .flatMap(this::extractCountryCodes)

    private fun extractCountryCodes(element: Element?): List<CountryCode> {
        if (element == null) {
            return emptyList()
        }

        val countryName = element
            .getElementsByIndexEquals(0)
            .text()
            .replace(nameCleanupRegexp, "")
            .trim()

        val codes = element
            .getElementsByIndexEquals(1)
            .select("a")
            .text()
            .let(this::extractPhoneExtensions)

        return codes.map { code ->
            CountryCode(
                code = code,
                country = countryName
            )
        }
    }

    private fun extractPhoneExtensions(link: String): List<String> {
        return phoneExtensionRegexp
            .findAll(link)
            .map { it.value.replace(" ", "").trim() }
            .toList()
    }
}
