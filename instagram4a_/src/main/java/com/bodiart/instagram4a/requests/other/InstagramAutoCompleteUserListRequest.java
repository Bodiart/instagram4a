package com.bodiart.instagram4a.requests.other;


import com.bodiart.instagram4a.payload.base.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramGetRequest;

import java.io.IOException;

/**
 * Created by root on 08/06/17.
 */

public class InstagramAutoCompleteUserListRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "friendships/autocomplete_user_list/";
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, StatusResult.class);
    }
}
