package com.setthasit.Covid19Worldwide.controller

import com.setthasit.Covid19Worldwide.adapter.BaseResBody
import com.setthasit.Covid19Worldwide.model.Country
import com.setthasit.Covid19Worldwide.model.Global
import com.setthasit.Covid19Worldwide.model.Summary
import com.setthasit.Covid19Worldwide.repository.SummaryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/summary")
class SummaryController {
    private val repo: SummaryRepository = SummaryRepository()

    @ResponseBody
    @GetMapping("")
    fun summary(): ResponseEntity<BaseResBody<Summary>> {
        return try {
            val data = repo.fetchSummary()
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body(BaseResBody(
                            status = HttpStatus.OK,
                            item = data
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
    @GetMapping("/global")
    fun global(): ResponseEntity<BaseResBody<Global>> {
        return try {
            val global = repo.fetchGlobal()
            ResponseEntity
                .status(HttpStatus.OK)
                .body(BaseResBody(
                        status = HttpStatus.OK,
                        item = global
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
    @GetMapping("/countries")
    fun countries(): ResponseEntity<BaseResBody<MutableList<Country>>> {
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
}