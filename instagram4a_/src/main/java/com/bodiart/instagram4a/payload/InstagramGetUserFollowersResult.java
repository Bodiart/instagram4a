package com.bodiart.instagram4a.payload;

import java.util.List;

public class InstagramGetUserFollowersResult extends StatusResult {
    public boolean big_list;
    public String next_max_id;
    public int page_size;

    public List<InstagramUserSummary> users;
}
