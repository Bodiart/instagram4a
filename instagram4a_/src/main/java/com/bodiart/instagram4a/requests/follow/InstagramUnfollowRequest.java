package com.bodiart.instagram4a.requests.follow;

import com.bodiart.instagram4a.payload.base.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


public class InstagramUnfollowRequest extends InstagramPostRequest<StatusResult> {

    private long userId;

    public InstagramUnfollowRequest(long userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        return "friendships/destroy/" + userId + "/";
    }

    @Override
    public String getPayload() throws IllegalAccessException, IOException, InstantiationException {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("user_id", userId);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
