package com.bodiart.instagram4a.requests;

import com.bodiart.instagram4a.payload.InstagramGetUserFollowersResult;

import java.io.IOException;

public class InstagramGetUserFollowersRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {

    private String maxId;
    private Long userId;

    public InstagramGetUserFollowersRequest(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        String baseUrl = "friendships/" + userId + "/followers/?rank_token=" + api.getRankToken();
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "&max_id=" + maxId;
        }

        return baseUrl;
    }

    @Override
    public InstagramGetUserFollowersResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, InstagramGetUserFollowersResult.class);
    }
}
