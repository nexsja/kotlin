package me.alexn.neotech

import me.alexn.neotech.application.Scrapper
import me.alexn.neotech.application.WikiScrapper
import me.alexn.neotech.infrastructure.persistance.local.CountryCodeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class AppConfig(
    @Value("\${scrapper.url}")
    val wikiUrl: String
) {

    @Bean
    @Profile("prod")
    fun wikiScrapper(repository: CountryCodeRepository): Scrapper {
        return WikiScrapper(wikiUrl, repository)
    }

    @Bean
    @Profile("test")
    fun testScrapper() {

    }

    @Bean
    fun simpleCorsFilter(): FilterRegistrationBean<*> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        // *** URL below needs to match the Vue client URL and port ***
        config.allowedOrigins = listOf("http://localhost:8081")
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")
        source.registerCorsConfiguration("/**", config)
        val bean: FilterRegistrationBean<*> = FilterRegistrationBean(CorsFilter(source))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}