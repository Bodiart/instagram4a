package com.bodiart.instagram4a.requests;

import androidx.annotation.NonNull;

import com.bodiart.instagram4a.InstagramConstants;
import com.bodiart.instagram4a.payload.InstagramGetUserFollowersResult;

import java.io.IOException;

public class InstagramGetUserFollowingRequest extends InstagramGetRequest<InstagramGetUserFollowersResult> {

    private String maxId;
    private Long userId;

    public InstagramGetUserFollowingRequest(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        String baseUrl = "friendships/" + userId + "/following/?rank_token=" + api.getRankToken();
//                + "&ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION;
        if (maxId != null && !maxId.isEmpty()) {
            baseUrl += "&max_id=" + maxId;
        }
        return baseUrl;
    }

    @Override
    public InstagramGetUserFollowersResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, InstagramGetUserFollowersResult.class);
    }
}
