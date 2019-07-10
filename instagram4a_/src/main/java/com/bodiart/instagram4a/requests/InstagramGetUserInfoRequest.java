package com.bodiart.instagram4a.requests;

import com.bodiart.instagram4a.payload.InstagramCurrentUserResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InstagramGetUserInfoRequest extends InstagramPostRequest<InstagramCurrentUserResult> {

    private Long userId;

    public InstagramGetUserInfoRequest(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getUrl() {
        Map<String, String> params = new HashMap<>();
        params.put("edit", "true");

//        return "accounts/current_user";
        return "users/" + userId + "/info";
    }

    @Override
    public String getPayload() throws IOException, IllegalAccessException, InstantiationException {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);
    }

    @Override
    public InstagramCurrentUserResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, InstagramCurrentUserResult.class);
    }
}
