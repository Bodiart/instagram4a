package com.bodiart.instagram4a.payload;

import java.io.Serializable;
import java.util.List;

public class InstagramCarouselMedia implements Serializable {

    public String pk;
    public String id;
    public String carousel_parent_id;
    public InstagramImageVersions_2 image_versions2;
    public List<InstagramVideoVersions> video_versions;
    public int media_type;

}
