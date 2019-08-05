package com.bodiart.instagram4a.requests.base;

import com.bodiart.instagram4a.Instagram4Android;
import com.bodiart.instagram4a.payload.StatusResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by root on 08/06/17.
 */

public abstract class InstagramRequest<T> {

    @JsonIgnore
    protected Instagram4Android api;

    /**
     * @return the url
     */
    public abstract String getUrl();

    /**
     * @return the method
     */
    public abstract String getMethod();

    /**
     * @return the payload
     */
    public String getPayload() throws IOException, IllegalAccessException, InstantiationException {
        return null;
    }

    /**
     * @return the result
     * @throws IOException
     */
    public abstract T execute() throws IOException, InstantiationException, IllegalAccessException;

    /**
     * Process response
     * @param resultCode Status Code
     * @param content Content
     */
    public abstract T parseResult(int resultCode, String content) throws IllegalAccessException, IOException, InstantiationException;

    /**
     * @return if request must be logged in
     */
    public boolean requiresLogin() {
        return true;
    }

    /**
     * Parses Json into type, considering the status code
     * @param statusCode HTTP Status Code
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    public <U> U parseJson(int statusCode, String str, Class<U> clazz) throws InstantiationException, IllegalAccessException, IOException {

        if (clazz.isAssignableFrom(StatusResult.class)) {

            //TODO: implement a better way to handle exceptions
            if (statusCode == 404) {
                StatusResult result = (StatusResult) clazz.newInstance();
                result.setStatus("error");
                result.setMessage("SC_NOT_FOUND");
                return (U) result;
            } else if (statusCode == 403) {
                StatusResult result = (StatusResult) clazz.newInstance();
                result.setStatus("error");
                result.setMessage("SC_FORBIDDEN");
                return (U) result;
            }
        }

        return parseJson(str, clazz);
    }

    /**
     * Parses Json into type
     * @param str Entity content
     * @param clazz Class
     * @return Result
     */
    public <U> U parseJson(String str, Class<U> clazz) throws IOException {
        //log.info("Reading " + clazz.getSimpleName() + " from " + str);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Log.d("HELLO", str);
        return objectMapper.readValue(str, clazz);
    }

    /**
     * Parses Json into type
     * @param is Entity stream
     * @param clazz Class
     * @return Result
     */
    public T parseJson(InputStream is, Class<T> clazz) throws IOException {
        return this.parseJson(readContent(is), clazz);
    }

    private String readContent(InputStream is) {
        String ret = "";
        try {
            String line;
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            StringBuffer out = new StringBuffer();

            while ((line = in.readLine()) != null) {
                out.append(line).append("\r\n");
            }
            ret = out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


    public Instagram4Android getApi() {
        return api;
    }

    public void setApi(Instagram4Android api) {
        this.api = api;
    }
}
