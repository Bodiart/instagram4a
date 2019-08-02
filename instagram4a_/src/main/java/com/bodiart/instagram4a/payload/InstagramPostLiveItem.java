package com.bodiart.instagram4a.payload;

import java.util.List;

public class InstagramPostLiveItem {

    public String pk;
    public InstagramUser user;
    public List<InstagramBroadcast> broadcasts;
    public String last_seen_broadcast_ts;
    public boolean can_reply;
    public int ranked_position;
    public int seen_ranked_position;
    public boolean muted;
    public boolean can_reshare;

}
