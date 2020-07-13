package com.setthasit.Covid19Worldwide.adapter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.http.HttpStatus

@JsonInclude(Include.NON_NULL)
class BaseResBody<T> (
        status: HttpStatus? = null,
        message: String? = null,
        item: T? = null,
        items: T? = null
) {
    var status: HttpStatus? = null
    var message: String? = null
    var item: T? = null
    var items: T? = null

    init {
        this.status = status
        this.message = message
        this.item = item
        this.items = items
    }
}