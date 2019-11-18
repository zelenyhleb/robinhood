package ru.krivocraft.robinhood;

import com.google.gson.annotations.SerializedName;

public class TokenDataSet {

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("expires_in")
    private final String expiresIn;

    public TokenDataSet(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }
}
