package com.bodiart.instagram4a.payload.feed;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;


public class InstagramReelsMediaFeedResult {

    @JsonProperty("reels")
    Map<String, InstagramFeedResult> reels;
}
