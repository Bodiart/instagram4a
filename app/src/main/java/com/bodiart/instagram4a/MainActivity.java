package com.bodiart.instagram4a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.bodiart.instagram4a.payload.feed.InstagramFeedResult;
import com.bodiart.instagram4a.payload.feed.InstagramReelsMediaFeedResult;
import com.bodiart.instagram4a.payload.feed.InstagramUserStoryFeedResult;
import com.bodiart.instagram4a.payload.base.StatusResult;
import com.bodiart.instagram4a.requests.feed.InstagramMakeStoryMediaSeen;
import com.bodiart.instagram4a.requests.feed.InstagramReelsMediaFeedRequest;
import com.bodiart.instagram4a.requests.feed.InstagramUserFeedRequest;
import com.bodiart.instagram4a.requests.feed.InstagramUserStoryFeedRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;

public class MainActivity extends AppCompatActivity {

    private Disposable queryDisp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.testBtn).setOnClickListener(v -> click());
    }

    private void click(){
        Instagram4Android instagram = new Instagram4Android();

        instagram.setup();
        instagram.auth(3473841221L);

        Map<String, Cookie> cookies = new HashMap<>();
        cookies.put("urlgen", buildCookie("urlgen", "{\"77.121.70.5\": 25229}:1hvG9X:NGsOfwJS0B3HhIVvEVAZdVyEhYQ"));
        cookies.put("ds_user_id", buildCookie("ds_user_id", "3473841221"));
        cookies.put("mid", buildCookie("mid", "XUptiwABAAGt81ohdNh3qf0-cxSv"));
        cookies.put("shbts", buildCookie("shbts", "1565158902.1663544"));
        cookies.put("sessionid", buildCookie("sessionid", "3473841221%3AdXwehBwzBvMaZj%3A21"));
        cookies.put("csrftoken", buildCookie("csrftoken", "GoNBoI7zl8fkuScAbqSQR9RjoGjdpQQS"));
        cookies.put("shbid", buildCookie("shbid", "14816"));
        cookies.put("rur", buildCookie("rur", "PRN"));

        instagram.addCookies(cookies);

        ArrayList<Long> ids = new ArrayList<>();
        ids.add(16186690516L);
        ids.add(4663846874L);

        Single.fromCallable(() -> instagram.sendRequest(new InstagramReelsMediaFeedRequest(ids)))
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<InstagramReelsMediaFeedResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InstagramReelsMediaFeedResult instagramReelsMediaFeedResult) {
                        System.out.println();
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Single.fromCallable(() -> instagram.sendRequest(new InstagramUserFeedRequest(4663846874L, "999999999", 0)))
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<InstagramFeedResult>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(InstagramFeedResult instagramFeedResult) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    private Cookie buildCookie(String key, String value) {

        return new Cookie.Builder()
                .domain("instagram.com")
                .path("/")
                .name(key)
                .value(value)
//                .expiresAt(HttpDate.MAX_DATE)
                .httpOnly()
                .secure()
                .build();
    }
}
