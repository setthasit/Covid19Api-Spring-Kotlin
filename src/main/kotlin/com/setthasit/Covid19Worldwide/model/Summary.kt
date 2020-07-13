package com.setthasit.Covid19Worldwide.model

class Summary(c: MutableList<Country>, g: Global) {
    var global: Global
        private set
    var countries: MutableList<Country>
        private set

    init {
        this.countries = c
        this.global = g
    }
}

class Global{
    var NewConfirmed: Long = 0
        private set
    var TotalConfirmed: Long = 0
        private set
    var newDeaths: Long = 0
        private set
    var totalDeaths: Long = 0
        private set
    var newRecovered: Long = 0
        private set
    var totalRecovered: Long = 0
        private set
}
