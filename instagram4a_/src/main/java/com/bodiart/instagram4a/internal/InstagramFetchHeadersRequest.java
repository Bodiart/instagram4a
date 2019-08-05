package com.bodiart.instagram4a.internal;

import com.bodiart.instagram4a.payload.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;
import com.bodiart.instagram4a.util.InstagramGenericUtil;

import java.io.IOException;

/**
 * Created by root on 08/06/17.
 */

public class InstagramFetchHeadersRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "si/fetch_headers/?challenge_type=signup&guid=" + InstagramGenericUtil.generateUuid(false);
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
