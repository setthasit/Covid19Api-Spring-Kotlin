package com.setthasit.Covid19Worldwide.controller

import com.setthasit.Covid19Worldwide.adapter.BaseResBody
import com.setthasit.Covid19Worldwide.model.Country
import com.setthasit.Covid19Worldwide.model.CountryCases
import com.setthasit.Covid19Worldwide.repository.CountryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/country")
class CountryController {
    private val repo: CountryRepository = CountryRepository()

    @ResponseBody
    @GetMapping("")
    fun allCountries(): ResponseEntity<BaseResBody<Array<Country>>> {
        return try {
            val countries = repo.fetchCountries()
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResBody(
                            status = HttpStatus.OK,
                            items = countries
                    ))
        } catch (error: Exception) {
            ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResBody(
                            status = HttpStatus.INTERNAL_SERVER_ERROR,
                            message = error.message
                    ))
        }
    }

    @ResponseBody
    @GetMapping("/{countrySlug}/daily-cases/{caseStatus}")
    fun countryDialyCases(
            @PathVariable("countrySlug") country: String,
            @PathVariable("caseStatus") status: String
    ): ResponseEntity<BaseResBody<Array<CountryCases>>> {
        return try {
            val countries = repo.fetchDayOneByCountry(country, status)
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResBody(
                            status = HttpStatus.OK,
                            items = countries
                    ))
        } catch (error: Exception) {
            ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(BaseResBody(
                            status = HttpStatus.INTERNAL_SERVER_ERROR,
                            message = error.message
                    ))
        }
    }
}