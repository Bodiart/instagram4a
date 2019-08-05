package com.bodiart.instagram4a.requests.feed;

import com.bodiart.instagram4a.payload.InstagramFeedResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;

import java.io.IOException;

public class InstagramUserFeedRequest extends InstagramGetRequest<InstagramFeedResult> {

    private long userId;
    private String maxId;
    private long minTimestamp;

    public InstagramUserFeedRequest(long userId, String maxId, long minTimestamp) {
        this.userId = userId;
        this.maxId = maxId;
        this.minTimestamp = minTimestamp;
    }

    @Override
    public String getUrl() {
        return "feed/user/" + userId + "/?max_id=" + maxId + "&min_timestamp=" + minTimestamp + "&rank_token=" + api.getRankToken() + "&ranked_content=true&";
    }

    @Override
    public InstagramFeedResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }

}