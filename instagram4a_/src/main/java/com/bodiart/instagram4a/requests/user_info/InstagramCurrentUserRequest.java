package com.bodiart.instagram4a.requests.user_info;

import com.bodiart.instagram4a.payload.user.InstagramCurrentUserResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InstagramCurrentUserRequest extends InstagramPostRequest<InstagramCurrentUserResult> {

    @Override
    public String getUrl() {
        Map<String, String> params = new HashMap<>();
        params.put("edit", "true");

//        return "accounts/current_user";
        return "users/" + api.getUserId() + "/info";
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
