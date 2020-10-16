package me.alexn.neotech

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

/**
 * [https://i.pinimg.com/474x/9c/72/d1/9c72d1fb5d61d8e71493c7d45c5e4612.jpg]
 */
inline fun <reified T> TestRestTemplate.get(url: String, vararg params: Pair<String, *>): ResponseEntity<T> =
    this.exchange(
        url,
        HttpMethod.GET,
        null,
        object: ParameterizedTypeReference<T>(){},
        params.toMap()
    )


