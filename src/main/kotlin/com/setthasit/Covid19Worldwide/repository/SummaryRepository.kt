package com.setthasit.Covid19Worldwide.repository

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.setthasit.Covid19Worldwide.model.Country
import com.setthasit.Covid19Worldwide.model.Global
import com.setthasit.Covid19Worldwide.model.Summary
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class SummaryRepository {
    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create()
    private val url: String = "https://api.covid19api.com/"
    private val client = HttpClient.newHttpClient()

    private fun invokeUrl(endpoint: String): URI {
        return URI(url + endpoint)
    }

    private fun summaryRequest(): HttpRequest {
        val request = HttpRequest.newBuilder(invokeUrl("summary")).build()
        return request
    }

    fun fetchSummary(): Summary {
        return try {
            val request = summaryRequest()
            val resp = client.send(request, BodyHandlers.ofString())
            if (resp.statusCode() != 200) {
                throw Exception("external api error, please try again later.")
            }
            gson.fromJson(resp.body(), Summary::class.java)
        } catch (error: Exception) {
            throw  error
        }
    }

    fun fetchGlobal(): Global {
        return try {
            val request = summaryRequest()
            val resp = client.send(request, BodyHandlers.ofString())
            if (resp.statusCode() != 200) {
                throw Exception("external api error, please try again later.")
            }
            gson.fromJson(resp.body(), Summary::class.java).global
        } catch (error: Exception) {
            throw  error
        }
    }

    fun fetchCountries(): MutableList<Country> {
        return try {
            val request = summaryRequest()
            val resp = client.send(request, BodyHandlers.ofString())
            if (resp.statusCode() != 200) {
                throw Exception("external api error, please try again later.")
            }
            gson.fromJson(resp.body(), Summary::class.java).countries
        } catch (error: Exception) {
            throw  error
        }
    }
}