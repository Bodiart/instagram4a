package com.bodiart.instagram4a.payload.user;

import com.bodiart.instagram4a.payload.base.StatusResult;

import java.util.LinkedHashMap;

/**
 * Created by root on 08/06/17.
 */

public class InstagramLoginResult extends StatusResult {

    private InstagramLoggedUser logged_in_user;
    private String error_type;
    private String checkpoint_url;
    private LinkedHashMap challenge; // url challenge


    public InstagramLoggedUser getLogged_in_user() {
        return logged_in_user;
    }

    public void setLogged_in_user(InstagramLoggedUser logged_in_user) {
        this.logged_in_user = logged_in_user;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public String getCheckpoint_url() {
        return checkpoint_url;
    }

    public void setCheckpoint_url(String checkpoint_url) {
        this.checkpoint_url = checkpoint_url;
    }

    public LinkedHashMap getChallenge() {
        return challenge;
    }

    public void setChallenge(LinkedHashMap challenge) {
        this.challenge = challenge;
    }
}
