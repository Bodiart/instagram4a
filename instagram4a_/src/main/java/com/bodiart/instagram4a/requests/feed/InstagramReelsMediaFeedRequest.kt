package com.bodiart.instagram4a.requests.feed

import com.bodiart.instagram4a.payload.base.StatusResult
import com.bodiart.instagram4a.requests.base.InstagramPostRequest

class InstagramReelsMediaFeedRequest : InstagramPostRequest<StatusResult>() {
    override fun getUrl(): String {
        return ""
    }

    override fun parseResult(resultCode: Int, content: String?): StatusResult {
        return StatusResult()
    }
}