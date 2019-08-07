package com.bodiart.instagram4a.payload.user;


/**
 * Created by root on 08/06/17.
 */

public class InstagramLoggedUser {
    private String profile_pic_url;
    private boolean allow_contacts_sync;
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_id;
    private long pk;
    private boolean is_verified;
    private boolean has_anonymous_profile_picture;

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public boolean isAllow_contacts_sync() {
        return allow_contacts_sync;
    }

    public void setAllow_contacts_sync(boolean allow_contacts_sync) {
        this.allow_contacts_sync = allow_contacts_sync;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public String getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(String profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean isHas_anonymous_profile_picture() {
        return has_anonymous_profile_picture;
    }

    public void setHas_anonymous_profile_picture(boolean has_anonymous_profile_picture) {
        this.has_anonymous_profile_picture = has_anonymous_profile_picture;
    }
}
