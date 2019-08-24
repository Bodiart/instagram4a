package com.bodiart.instagram4a.refactor.kotlin.payload.base

import com.fasterxml.jackson.annotation.JsonProperty

open class StatusResult {

        @JsonProperty("status")
        var status: String = ""

        @JsonProperty("message")
        var message: String? = null
}