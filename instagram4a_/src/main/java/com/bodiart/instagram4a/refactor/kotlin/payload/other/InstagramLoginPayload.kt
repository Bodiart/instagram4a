package com.bodiart.instagram4a.refactor.kotlin.payload.other

data class InstagramLoginPayload (
        val username: String,
        val password: String,
        val phoneId: String,
        val _csrftoken: String,
        val guid: String,
        val devideId: String,
        val loginAttemptAccount: Int = 0
)