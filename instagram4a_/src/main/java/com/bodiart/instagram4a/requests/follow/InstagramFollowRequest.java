package com.bodiart.instagram4a.requests.follow;

import com.bodiart.instagram4a.payload.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class InstagramFollowRequest extends InstagramPostRequest<StatusResult> {

    private long userId;

    public InstagramFollowRequest(long userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        return "friendships/create/" + userId + "/";
    }

    @Override
    public String getPayload() throws IOException, IllegalAccessException, InstantiationException {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("user_id", userId);
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);

    }

    @Override
    public StatusResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
