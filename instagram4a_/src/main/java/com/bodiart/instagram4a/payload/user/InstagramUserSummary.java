package com.bodiart.instagram4a.payload.user;

import java.io.Serializable;
import java.util.Objects;

public class InstagramUserSummary implements Serializable {
    public boolean is_verified;
    public String profile_pic_id;
    public boolean is_favorite;
    public boolean is_private;
    public String username;
    public long pk;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public String full_name;
    public Long latest_reel_media;

    @Override
    public int hashCode() {
        return Objects.hash(username, pk);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof InstagramUserSummary)) {
            return false;
        }

        InstagramUserSummary user = (InstagramUserSummary) obj;
        return pk == user.pk;

    }
}