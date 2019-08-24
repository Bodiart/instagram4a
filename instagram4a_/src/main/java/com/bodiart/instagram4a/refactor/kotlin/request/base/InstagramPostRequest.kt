package com.bodiart.instagram4a.refactor.kotlin.request.base

import com.bodiart.instagram4a.refactor.kotlin.utils.InstagramConstants
import com.bodiart.instagram4a.refactor.kotlin.utils.InstagramHashUtil
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody

abstract class InstagramPostRequest <T> : InstagramRequest<T>() {

    override fun getMethod() = "POST"

    override fun execute(): T {

        var baseUrl = InstagramConstants.API_URL
        if (requireApiV2())
            baseUrl = InstagramConstants.API_URL2

        val request = Request.Builder()
                .url(baseUrl + getUrl())
                .addHeader("Connection", "close")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie2", "\$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("X-IG-Capabilities", "3boBAA==")
                .addHeader("X-IG-Connection-Type", "WIFI")
                .addHeader("X-IG-Connection-Speed", "-1kbps")
                .addHeader("X-IG-App-ID", "567067343352427")
                .addHeader("User-Agent", InstagramConstants.USER_AGENT)
                .post(RequestBody.create("application/x-www-form-urlencoded".toMediaTypeOrNull(),
                        InstagramHashUtil.generateSignature(getPayload())))
                .build()

        val response = api.client.newCall(request).execute()

        return parseResult(response.code, response.body?.string())
    }
}