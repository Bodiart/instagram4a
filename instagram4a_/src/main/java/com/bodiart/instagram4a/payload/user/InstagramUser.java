package com.bodiart.instagram4a.payload.user;

import java.io.Serializable;

public class InstagramUser implements Serializable {
    //users/ + api.getUserId() + /info

    public long pk;
    public String username;
    public String full_name;
    public boolean is_private;
    public String profile_pic_url;
    public String profile_pic_id;
    public boolean is_verified;
//    public boolean has_anonymous_profile_picture;
    public int media_count;
//    public int geo_media_count;
    public int follower_count;
    public int following_count;
//    public int following_tag_count;
    public String biography;
//    public String external_url;
//    public boolean can_boost_post;
//    public boolean can_see_organic_insights;
//    public boolean show_insights_terms;
//    public boolean can_convert_to_business;
//    public boolean can_create_sponsor_tags;
//    public boolean can_be_tagged_as_sponsor;
//    public int total_igtv_videos;
//    public int total_ar_effects;
//    public String reel_auto_archive;
//    public boolean is_profile_action_needed;
//    public int usertags_count;
//    public boolean usertag_review_enabled;
//    public boolean is_needy;
//    public boolean is_interest_account;
//    public boolean has_chaining;
//    public List<InstagramProfilePic> hd_profile_pic_versions;
//    public InstagramProfilePic hd_profile_pic_url_info;
//    public boolean has_placed_orders;
//    public boolean can_tag_products_from_merchants;
//    public boolean show_conversion_edit_entry;
//    public boolean aggregate_promote_engagement;
//    public String allowed_commenter_type;
//    public boolean is_video_creator;
//    public boolean has_profile_video_feed;  check
//    public boolean has_highlight_reels;
//    public boolean is_eligible_to_show_fb_cross_sharing_nux;
//    public boolean page_id_for_new_suma_biz_account;
//    public boolean eligible_shopping_signup_entrypoints;
//    public boolean can_be_reported_as_fraud;
//    public boolean is_business;
//    public int account_type;
//    public String is_call_to_action_enabled;
//    public boolean include_direct_blacklist_status;
//    public boolean can_follow_hashtag;
//    public boolean is_potential_business;
//    public boolean feed_post_reshare_disabled;
//    public int besties_count;
//    public boolean show_besties_badge;
//    public int recently_bestied_by_count;
//    public boolean auto_expand_chaining;
//    public boolean highlight_reshare_disabled;







    // accounts/current_user
//    public long pk;
//    public String username;
//    public String full_name;
//    public boolean is_private;
//    public String profile_pic_url;
//    public String profile_pic_id; //todo somewhere need to change String to bool
//    public boolean is_verified;
//    public boolean has_anonymous_profile_picture;
//    public String biography;
//    public String external_url;
//    public String reel_auto_archive;
//    public List<InstagramProfilePic> hd_profile_pic_versions;
//    public InstagramProfilePic hd_profile_pic_url_info;
//    public String show_conversion_edit_entry;
//    public String allowed_commenter_type;
//    public String birthday;


    @Override
    public String toString() {
        return "InstagramUser{" +
                "pk=" + pk +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                ", is_private=" + is_private +
                ", profile_pic_url='" + profile_pic_url + '\'' +
                ", profile_pic_id='" + profile_pic_id + '\'' +
                ", is_verified=" + is_verified +
                ", media_count=" + media_count +
                ", follower_count=" + follower_count +
                ", following_count=" + following_count +
                ", biography='" + biography + '\'' +
                '}';
    }
}
