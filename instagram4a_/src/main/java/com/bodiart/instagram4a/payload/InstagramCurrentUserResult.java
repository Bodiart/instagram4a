package com.bodiart.instagram4a.payload;

import java.util.Map;

public class InstagramCurrentUserResult extends StatusResult {

    public InstagramUser user;
//    public Map<String, Object> user;


    @Override
    public String toString() {
        return "InstagramCurrentUserResult{" +
                "user=\n" + user +
                '}';
    }
}
