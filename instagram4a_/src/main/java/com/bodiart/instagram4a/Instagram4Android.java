package com.bodiart.instagram4a;


import com.bodiart.instagram4a.internal.InstagramFetchHeadersRequest;
import com.bodiart.instagram4a.payload.InstagramLoginPayload;
import com.bodiart.instagram4a.payload.InstagramLoginResult;
import com.bodiart.instagram4a.requests.base.InstagramRequest;
import com.bodiart.instagram4a.requests.other.InstagramLoginRequest;
import com.bodiart.instagram4a.util.InstagramGenericUtil;
import com.bodiart.instagram4a.util.InstagramHashUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;



public class Instagram4Android {

    private String deviceId;
    private String username = "";
    private String password = "";
    private String accessToken;
    private boolean isLoggedIn;
    private String uuid;
    private String rankToken;
    private long userId;
    private Response lastResponse;
    private OkHttpClient client;
    private final HashMap<String, Cookie> cookieStore = new HashMap<>();

    public Instagram4Android(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Instagram4Android() {
    }

    public void setup() {
        this.deviceId = InstagramHashUtil.generateDeviceId(this.username, this.password);
        this.uuid = InstagramGenericUtil.generateUuid(true);

        client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        if (cookies != null) {
                            for (Cookie cookie : cookies)
                                cookieStore.put(cookie.name(), cookie);
                        }
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> validCookies = new ArrayList<>();
                        for (Map.Entry<String, Cookie> entry : cookieStore.entrySet()) {
                            Cookie cookie = entry.getValue();

                            if(cookie.expiresAt() >= System.currentTimeMillis())
                                validCookies.add(cookie);
                        }
                        return validCookies;
                    }
                })
                .build();

    }

    @Deprecated // now need to login by browser
    public InstagramLoginResult login() throws Exception {

        InstagramLoginPayload loginRequest = new InstagramLoginPayload();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        loginRequest.setGuid(uuid);
        loginRequest.setDevice_id(deviceId);
        loginRequest.setPhone_id(InstagramGenericUtil.generateUuid(true));
        loginRequest.setLogin_attempt_account(0);
        loginRequest.set_csrftoken(getOrFetchCsrf(null));

        InstagramLoginResult loginResult = this.sendRequest(new InstagramLoginRequest(loginRequest));
        // OK
        if (loginResult.getStatus().equalsIgnoreCase(InstagramConstants.AUTH_OK)) {
            auth(loginResult.getLogged_in_user().getPk());
        }
        // CHALLENGE REQUIRED
//        else if (loginResult.getStatus().equalsIgnoreCase(InstagramConstants.AUTH_CHALLENGE_REQUIRED)) {
//            // TODO impl
//        }

        return loginResult;
    }

    public void auth(long userID) {

        this.userId = userID;

        this.rankToken = this.userId + "_" + this.uuid;
        this.isLoggedIn = true;
    }

    public String getOrFetchCsrf(HttpUrl url) throws IOException, InstantiationException, IllegalAccessException {

        Cookie cookie = getCsrfCookie(url);
        if(cookie == null) {
            sendRequest(new InstagramFetchHeadersRequest());
            cookie = getCsrfCookie(url);
        }

        return cookie.value();
    }

    private Cookie getCsrfCookie(HttpUrl url) {

        for(Cookie cookie: client.cookieJar().loadForRequest(url)) {
            if (cookie.name().equalsIgnoreCase("csrftoken"))
                return cookie;
        }

        return null;
    }

    public <T> T sendRequest(InstagramRequest<T> request) throws IOException, IllegalAccessException, InstantiationException {

        if (!this.isLoggedIn
                && request.requiresLogin()) {
            throw new IllegalStateException("Need to login first!");
        }

        request.setApi(this);
        T response = request.execute();

        return response;
    }





    public String getUuid() {
        return uuid;
    }

    public String getRankToken() {
        return rankToken;
    }

    public long getUserId() {
        return userId;
    }

    public Response getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(Response lastResponse) {
        this.lastResponse = lastResponse;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public HashMap<String, Cookie> getCookieStore() {
        return cookieStore;
    }

    public void addCookie(String key, Cookie cookie){
        cookieStore.put(key, cookie);
    }

    public void addCookies(Map<String, Cookie> addCookies){
        cookieStore.putAll(addCookies);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getDeviceId() {
        return deviceId;
    }
}