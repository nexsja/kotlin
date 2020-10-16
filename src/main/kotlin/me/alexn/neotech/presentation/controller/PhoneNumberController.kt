package me.alexn.neotech.presentation.controller

import me.alexn.neotech.application.domain.CountryCode
import me.alexn.neotech.application.domain.CountryCodeRepository
import me.alexn.neotech.presentation.validation.ValidPhone
import me.alexn.neotech.presentation.dto.*
import me.alexn.neotech.presentation.error.NotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class PhoneNumberController(private val repository: CountryCodeRepository) {

    @GetMapping("/country")
    fun fetchCountry(
        @ValidPhone
        @RequestParam
        phoneNumber: String
    ): ApiResponse<CountryCode> {
        return repository.findByPhone(phoneNumber)?.toApiResponse()
            ?: throw NotFoundException()
    }
}
