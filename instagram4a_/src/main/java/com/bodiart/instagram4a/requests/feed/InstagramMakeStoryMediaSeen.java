package com.bodiart.instagram4a.requests.feed;

import com.bodiart.instagram4a.payload.InstagramFeedItem;
import com.bodiart.instagram4a.payload.StatusResult;
import com.bodiart.instagram4a.requests.base.InstagramPostRequest;
import com.bodiart.instagram4a.util.InstagramHashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InstagramMakeStoryMediaSeen extends InstagramPostRequest<StatusResult> {

    private List<InstagramFeedItem> feedItems;

    public InstagramMakeStoryMediaSeen(List<InstagramFeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    @Override
    public String getUrl() {
        return "media/seen/?reel=1&live_vod=0";
    }

    @Override
    public String getPayload() throws IOException, IllegalAccessException, InstantiationException {

        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("_uuid", api.getUuid());
        likeMap.put("_uid", api.getUserId());
        likeMap.put("_csrftoken", api.getOrFetchCsrf(null));
        likeMap.put("container_module", "feed_timeline");
        likeMap.put("reels", makeReels());
        likeMap.put("reel_media_skipped", Collections.emptyList());
        likeMap.put("live_vods", Collections.emptyList());
        likeMap.put("live_vods_skipped", Collections.emptyList());
        likeMap.put("nuxes", Collections.emptyList());
        likeMap.put("nuxes_skipped", Collections.emptyList());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(likeMap);
    }

    @Override
    public StatusResult parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(resultCode, content, StatusResult.class);
    }

    private Map<String, String> makeReels(){
        // Build the list of seen media, with human randomization of seen-time.
        Map<String, String> reels = new HashMap<>();

        Long maxSeenAt = System.currentTimeMillis() / 1000; // Get current global UTC timestamp.
        Long seenAt = maxSeenAt - (3 * feedItems.size());

        for (InstagramFeedItem item : feedItems){

            // Raise "seenAt" if it's somehow older than the item's "takenAt".
            // NOTE: Can only happen if you see a story instantly when posted.
            Long itemTakenAt = item.taken_at;
            if (seenAt < itemTakenAt){
                seenAt = itemTakenAt + 2;
            }

            // Do not let "seenAt" exceed the current global UTC time.
            if (seenAt > maxSeenAt){
                seenAt = maxSeenAt;
            }

            // Determine the source ID for this item. This is where the item was
            // seen from, such as a UserID or a Location-StoryTray ID.
            Long itemSourceId = item.user.pk;

            // Key Format: "mediaPk_userPk_sourceId".
            // NOTE: In case of seeing stories on a user's profile, their
            // userPk is used as the sourceId, as "mediaPk_userPk_userPk".
            String reelId = item.id + "_" + itemSourceId;

            // Value Format: ["mediaTakenAt_seenAt"] (array with single string).
            reels.put(reelId, itemTakenAt + "_" + seenAt);

            // Randomly add 1-3 seconds to next seenAt timestamp, to act human.
            seenAt += new Random().nextInt(3 - 1 + 1) + 1;

        }

//        return InstagramHashUtil.mapToString(reels, ",");
        return reels;
    }
}
