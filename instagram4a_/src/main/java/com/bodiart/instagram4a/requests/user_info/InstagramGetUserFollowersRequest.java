package com.bodiart.instagram4a.requests.user_info;

import com.bodiart.instagram4a.payload.InstagramGetUserFollowersResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;

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
