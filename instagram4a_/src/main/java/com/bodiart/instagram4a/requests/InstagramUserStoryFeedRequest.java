package com.bodiart.instagram4a.requests;

import com.bodiart.instagram4a.payload.InstagramUserStoryFeedResult;

import java.io.IOException;

public class InstagramUserStoryFeedRequest extends InstagramGetRequest<InstagramUserStoryFeedResult> {

    private String userId;

    @Override
    public String getUrl() {
        return String.format("feed/user/%s/story/", userId);
    }

    @Override
    public InstagramUserStoryFeedResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, InstagramUserStoryFeedResult.class);
    }
}
