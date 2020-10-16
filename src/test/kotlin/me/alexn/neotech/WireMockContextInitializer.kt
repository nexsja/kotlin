package me.alexn.neotech

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class WireMockContextInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {

        val wmServer = WireMockServer(WireMockConfiguration().port(10230))
        wmServer.start()
        wmServer.stubFor(WireMock.get("/wiki/List_of_country_calling_codes")
            .willReturn(
                WireMock.aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                    .withBody(getResourceAsText("/wiki.html")))
        )

        applicationContext.beanFactory.registerSingleton("wireMock", wmServer)
        applicationContext.addApplicationListener {
            if (it is ContextClosedEvent) {
                wmServer.stop()
            }
        }
    }

    private fun getResourceAsText(path: String): String = javaClass.getResource(path)!!.readText()
}