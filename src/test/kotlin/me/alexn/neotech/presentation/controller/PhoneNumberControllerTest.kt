package me.alexn.neotech.presentation.controller

import me.alexn.neotech.IntegrationTest
import me.alexn.neotech.application.domain.CountryCode
import me.alexn.neotech.get
import me.alexn.neotech.presentation.dto.ApiResponse
import me.alexn.neotech.presentation.error.AppError
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class PhoneNumberControllerTest : IntegrationTest() {
    @Test
    fun `an error is thrown when the supplied phone number is incorrectly formatted`() {
        val response = http.get<ApiResponse<AppError>>("/country?phoneNumber=NotAPhoneNumber")
        val expected = ApiResponse("ERROR", AppError("fetchCountry.phoneNumber: Invalid phone format: Phone number must start with a plus sign (+) and be at least 6 digits long"))

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(expected)
    }

    @Test
    fun `an error is thrown when phone number length is less than 6 chars`() {
        val response = http.get<ApiResponse<AppError>>("/country?phoneNumber=37199")
        val expected = ApiResponse("ERROR", AppError("fetchCountry.phoneNumber: Invalid phone format: Phone number must start with a plus sign (+) and be at least 6 digits long"))

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(response.body).isEqualTo(expected)
    }

    @Test
    fun `a phone number is validated and a country is returned`() {
        val response = http.get<ApiResponse<CountryCode>>("/country?phoneNumber={phoneNumber}", "phoneNumber" to "+37199999")
        val expected = ApiResponse("OK", CountryCode("+371", "Latvia"))

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(expected)
    }
}