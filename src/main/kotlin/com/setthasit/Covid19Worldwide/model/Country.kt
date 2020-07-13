package com.setthasit.Covid19Worldwide.model

import com.fasterxml.jackson.annotation.JsonInclude
import javax.naming.Context

@JsonInclude(JsonInclude.Include.NON_NULL)
open class Country {
    var country: String = ""
        private set
    var countryCode: String = ""
        private set
    var ISO2: String? = null
        private set
    var slug: String? = null
        private set
    var newConfirmed: Long? = null
        private set
    var totalConfirmed: Long? = null
        private set
    var newDeaths: Long? = null
        private set
    var totalDeaths: Long? = null
        private set
    var newRecovered: Long? = null
        private set
    var totalRecovered: Long? = null
        private set
    var date: String? = null
        private set
}

class CountryCases: Country {
    constructor()

    var lat: String? = null
        private set
    var lon: String? = null
        private set
    var cases: Long? = null
        private set
    var status: String? = null
        private set
}