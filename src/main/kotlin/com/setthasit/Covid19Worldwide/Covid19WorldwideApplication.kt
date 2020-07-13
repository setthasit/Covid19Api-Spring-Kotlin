package com.setthasit.Covid19Worldwide

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.GsonHttpMessageConverter

@SpringBootApplication
class Covid19WorldwideApplication {
    @Bean
    fun gsonHttpMessageConverter(gson: Gson): GsonHttpMessageConverter {
        val converter =  GsonHttpMessageConverter()
        converter.setGson(gson)
        return converter
    }
}

fun main(args: Array<String>) {
    runApplication<Covid19WorldwideApplication>(*args)
}
