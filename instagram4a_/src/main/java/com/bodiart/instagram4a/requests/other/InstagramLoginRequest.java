package com.bodiart.instagram4a.requests.other;

import com.bodiart.instagram4a.payload.user.InstagramLoginPayload;
import com.bodiart.instagram4a.payload.user.InstagramLoginResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by root on 09/06/17.
 */

public class InstagramLoginRequest extends InstagramPostRequest<InstagramLoginResult> {

    private InstagramLoginPayload payload;

    public InstagramLoginRequest(InstagramLoginPayload payload) {
        this.payload = payload;
    }

    @Override
    public String getUrl() {
        return "accounts/login/";
    }

    @Override
    public String getPayload() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(payload);
    }

    @Override
    public InstagramLoginResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, InstagramLoginResult.class);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }
}
