package ru.krivocraft.robinhood.model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("secret")
    private final String secret;

    @SerializedName("device_id")
    private final String deviceId;

    public Token(String accessToken, String secret, String deviceId) {
        this.accessToken = accessToken;
        this.secret = secret;
        this.deviceId = deviceId;
    }

    public static Token empty() {
        return new Token("", "", "");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
