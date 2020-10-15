package me.alexn.neotech.eventlistener

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import java.io.File

@Component
class ApplicationReadyListener(val repository: CountryCodeRepository) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println("Fetching Wiki")

//        val doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_country_calling_codes").get()

        val doc = Jsoup.parse(File("wiki.html").readText())

        doc.select("h2:contains(Alphabetical listing by country or region)")
            .next()
            .select("tbody > tr")
            .map { extractCountryCodes(it) }
            .forEach { repository.store(it) }

        println("Done")

    }

    private fun extractCountryCodes(it: Element?): List<CountryCode> {
        val countryName = it?.getElementsByIndexEquals(0)?.text()?.trim() ?: ""
        val codes = it?.getElementsByIndexEquals(1)?.select("a")?.text()?.let { extractExtensions(it) }

        return codes!!.map {
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
