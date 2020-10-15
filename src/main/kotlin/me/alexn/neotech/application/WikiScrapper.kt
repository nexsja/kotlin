package me.alexn.neotech.application

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File

class WikiScrapper(val wikiUrl: String, private val repository: CountryCodeRepository) : Scrapper {
    override fun scrape() {
//        val doc = Jsoup.connect(wikiUrl).get()
        val doc = Jsoup.parse(File("wiki.html").readText())
        println(wikiUrl)
        doc.select("h2:contains(Alphabetical listing by country or region)")
            .next()
            .select("tbody > tr")
            .flatMap(this::extractCountryCodes)
            .let(repository::store)
    }

    private fun extractCountryCodes(it: Element?): List<CountryCode> {
        if (it == null) {
            return emptyList()
        }

        val countryName = it.getElementsByIndexEquals(0).text().trim()
        val codes = it.getElementsByIndexEquals(1).select("a").text().let { extractExtensions(it) }

        return codes.map {
            CountryCode(it, countryName.replace(Regex("""\[notes\s?\d+?\]"""), "").trim())
        }
    }

    private fun extractExtensions(link: String): List<String> {
        return Regex("""\+([\d\s]+)""")
            .findAll(link)
            .map { it.value.trim().replace(" ", "") }
            .toCollection(mutableListOf())
            .toList()
    }
}