package com.bodiart.instagram4a.payload.feed;

import com.bodiart.instagram4a.payload.media.InstagramBroadcast;
import com.bodiart.instagram4a.payload.media.InstagramLocation;
import com.bodiart.instagram4a.payload.user.InstagramUser;

import java.util.List;

public class InstagramReel {

    public String id;
    public List<InstagramFeedItem> items;
    public InstagramUser user;
    public float expiring_at;
    public float seen;
    public boolean can_reply;
    public InstagramLocation location;
    public String latest_reel_media;
    public int prefetch_count;
    public InstagramBroadcast broadcast;

}
