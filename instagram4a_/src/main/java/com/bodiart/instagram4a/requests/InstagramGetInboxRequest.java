package com.bodiart.instagram4a.requests;

import com.bodiart.instagram4a.payload.StatusResult;

import java.io.IOException;

/**
 * Created by root on 09/06/17.
 */

public class InstagramGetInboxRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "direct_v2/inbox/?";
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
