package com.bodiart.instagram4a.requests.other;


import com.bodiart.instagram4a.payload.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;

import java.io.IOException;

/**
 * Created by root on 09/06/17.
 */

public class InstagramGetRecentActivityRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "news/inbox/?";
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, StatusResult.class);
    }
}
