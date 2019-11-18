package ru.krivocraft.robinhood.network.datasets;

import com.google.gson.annotations.SerializedName;

public class TokenDataSet {

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("secret")
    private final String secret;

    @SerializedName("user_id")
    private final String userId;

    @SerializedName("error")
    private final String error;

    private final String deviceId;

    public TokenDataSet(String accessToken, String secret, String userId, String error, String deviceId) {
        this.accessToken = accessToken;
        this.secret = secret;
        this.userId = userId;
        this.error = error;
        this.deviceId = deviceId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public String getUserId() {
        return userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getError() {
        return error;
    }
}
