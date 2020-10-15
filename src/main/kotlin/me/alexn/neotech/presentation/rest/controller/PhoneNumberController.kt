package me.alexn.neotech.presentation.rest.controller

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import me.alexn.neotech.presentation.annotation.ValidPhone
import me.alexn.neotech.presentation.dto.*
import me.alexn.neotech.presentation.exception.NotFoundException
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class PhoneNumberController(val repository: CountryCodeRepository) {

    @GetMapping("country")
    fun fetchCountry(
            @ValidPhone
            @RequestParam
            phoneNumber: String
    ): ApiResponse<CountryCode> {

        return repository.findCountryByPhone(phoneNumber)?.toApiResponse()
                ?: throw NotFoundException()
    }
}