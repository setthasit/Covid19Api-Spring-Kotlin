package com.setthasit.Covid19Worldwide.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.setthasit.Covid19Worldwide.model.Country
import com.setthasit.Covid19Worldwide.model.CountryCases
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class CountryRepository {
    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create()
    private val url: String = "https://api.covid19api.com/"
    private val client = HttpClient.newHttpClient()

    private fun invokeUrl(endpoint: String): URI {
        return URI(url + endpoint)
    }

    private fun countriesRequest(): HttpRequest {
        return HttpRequest.newBuilder(invokeUrl("countries")).build()
    }

    private fun dayOneCountryRequest(country: String, status: String): HttpRequest {
        // case status: confirmed, recovered, deaths
        return HttpRequest.newBuilder(invokeUrl("dayone/country/${country}/status/${status}")).build()
    }

    private fun dayOneAllCountryRequest(country: String): HttpRequest {
        return HttpRequest.newBuilder(invokeUrl("dayone/country/${country}")).build()
    }

    fun fetchCountries(): Array<Country> {
        return try {
            val request = countriesRequest()
            val resp = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (resp.statusCode() != 200) {
                throw Exception("external api error, please try again later.")
            }
            gson.fromJson(resp.body(), Array<Country>::class.java)
        } catch (error: Exception) {
            throw error
        }
    }

    fun fetchDayOneByCountry(country: String, status: String): Array<CountryCases> {
        return try {
            val request = if (status == "all") {
                dayOneAllCountryRequest(country)
            } else {
                dayOneCountryRequest(country, status)
            }
            val resp = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (resp.statusCode() != 200) {
                throw Exception("external api error, please try again later.")
            }
            gson.fromJson(resp.body(), Array<CountryCases>::class.java)
        } catch (error: Exception) {
            throw error
        }
    }
}