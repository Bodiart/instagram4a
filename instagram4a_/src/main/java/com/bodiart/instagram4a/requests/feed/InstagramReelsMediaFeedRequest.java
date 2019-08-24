package com.bodiart.instagram4a.requests.feed;

import com.bodiart.instagram4a.payload.base.StatusResult;
import com.bodiart.instagram4a.payload.feed.InstagramReelsMediaFeedResult;
import com.bodiart.instagram4a.payload.feed.InstagramUserStoryFeedResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InstagramReelsMediaFeedRequest extends InstagramPostRequest<InstagramReelsMediaFeedResult> {

    private ArrayList<Long> ids;

    public InstagramReelsMediaFeedRequest(ArrayList<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String getUrl() {
        return "feed/reels_media/";
    }

    @Override
    public String getPayload() throws IOException, IllegalAccessException, InstantiationException {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("user_ids", ids);
        likeMap.put("source", "feed_timeline");

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);
    }

    @Override
    public InstagramReelsMediaFeedResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, InstagramReelsMediaFeedResult.class);
    }
}
