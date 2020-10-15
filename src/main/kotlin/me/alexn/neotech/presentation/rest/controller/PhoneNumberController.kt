package me.alexn.neotech.presentation.rest.controller

import me.alexn.neotech.domain.CountryCode
import me.alexn.neotech.domain.CountryCodeRepository
import me.alexn.neotech.presentation.dto.ApiResponse
import me.alexn.neotech.presentation.dto.PhoneNumber
import me.alexn.neotech.presentation.dto.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PhoneNumberController(val repository: CountryCodeRepository) {

    @GetMapping("country")
    fun fetchCountry(@RequestParam("phonenumber") dto: PhoneNumber): ApiResponse<CountryCode?> {

        val country = repository.findCountryByPhone(dto.number)


        return ok(country)
    }

}