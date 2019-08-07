package com.bodiart.instagram4a.util;

import com.bodiart.instagram4a.InstagramConstants;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by root on 08/06/17.
 */

public class InstagramHashUtil {

    public static final String XLATE = "0123456789abcdef";

    protected static String digest(String codec, String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance(codec);
            byte[] digestBytes = digest.digest(source.getBytes(Charset.forName("UTF-8")));
            return hexlate(digestBytes, digestBytes.length);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException(codec + " codec not available");
        }
    }

    public static String md5hex(String source) {
        return digest("MD5", source);
    }

    protected static String hexlate(byte[] bytes, int initialCount) {
        if (bytes == null) {
            return "";
        }

        int count = Math.min(initialCount, bytes.length);
        char[] chars = new char[count * 2];

        for (int i = 0; i < count; ++i) {
            int val = bytes[i];
            if (val < 0) {
                val += 256;
            }
            chars[(2 * i)] = XLATE.charAt(val / 16);
            chars[(2 * i + 1)] = XLATE.charAt(val % 16);
        }

        return new String(chars);
    }

    public static String generateDeviceId(String username, String password) {
        String seed = md5hex(username + password);
        String volatileSeed = "12345";

        return "android-" + md5hex(seed + volatileSeed).substring(0, 16);
    }

    public static String generateHash(String key, String string) {
        SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init((Key) object);
            byte[] byteArray = mac.doFinal(string.getBytes("UTF-8"));
            return new String(new Hex().encode(byteArray), "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateSignature(String payload) throws UnsupportedEncodingException {
        String parsedData = URLEncoder.encode(payload, "UTF-8");

        String signedBody = generateHash(InstagramConstants.API_KEY, payload);

        return "ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'
                + parsedData;

    }

    public static String mapToString(Map<String, String> map, String separator){

//        {
//            "1309763051087626108_124317_124317": ["1470355944_1470372029"],
//            "1309764045355643149_124317_124317": ["1470356063_1470372039"],
//            "1309818450243415912_124317_124317": ["1470362548_1470372060"],
//            "1309764653429046112_124317_124317": ["1470356135_1470372049"],
//            "1309209597843679372_124317_124317": ["1470289967_1470372013"]
//        }

//        "reels" -> "{2104627324929802811_16186690516_16186690516=[1565111149_1565160491]}"

        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append("\"").append(key).append("\"").append(":").append("[\"").append(map.get(key)).append("\"]").append(separator);
        }
        mapAsString.deleteCharAt(mapAsString.length() - 1).append("}");
        return mapAsString.toString();
    }

}