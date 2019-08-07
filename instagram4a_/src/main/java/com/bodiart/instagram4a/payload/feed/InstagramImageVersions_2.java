package com.bodiart.instagram4a.payload.feed;

import com.bodiart.instagram4a.payload.media.InstagramCandidate;

import java.io.Serializable;
import java.util.List;

public class InstagramImageVersions_2 implements Serializable {

    public List<InstagramCandidate> candidates;
    public String trace_token;

}
