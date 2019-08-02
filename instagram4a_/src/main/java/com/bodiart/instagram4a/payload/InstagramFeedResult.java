package com.bodiart.instagram4a.payload;

import java.util.List;

public class InstagramFeedResult extends StatusResult {

    public int num_results;
    public boolean auto_load_more_enabled;
    public boolean more_available;
    //private boolean is_direct_v2_enabled;
    public String next_max_id;

    public List<InstagramFeedItem> items;

}
