package com.bodiart.instagram4a.requests.feed;

import com.bodiart.instagram4a.payload.feed.InstagramUserStoryFeedResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;

import java.io.IOException;

public class InstagramUserStoryFeedRequest extends InstagramGetRequest<InstagramUserStoryFeedResult> {

    private String userId;

    public InstagramUserStoryFeedRequest(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        return String.format("feed/user/%s/story/", userId);
    }

    @Override
    public InstagramUserStoryFeedResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, InstagramUserStoryFeedResult.class);
    }
}
