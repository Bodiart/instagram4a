package com.bodiart.instagram4a.requests;


import com.bodiart.instagram4a.payload.StatusResult;

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
