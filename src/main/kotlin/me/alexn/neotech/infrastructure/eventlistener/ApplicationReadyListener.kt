package me.alexn.neotech.infrastructure.eventlistener

import me.alexn.neotech.application.Scrapper
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class ApplicationReadyListener(val scrapper: Scrapper) : ApplicationListener<ApplicationReadyEvent> {


    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println("Fetching Wiki")

        scrapper.scrape()

        println("Done")

    }
}
