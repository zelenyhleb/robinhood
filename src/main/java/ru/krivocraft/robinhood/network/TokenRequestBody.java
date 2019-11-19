package ru.krivocraft.robinhood.network;

import com.google.gson.annotations.SerializedName;
import ru.krivocraft.robinhood.model.TokenData;

public class TokenRequestBody {
    @SerializedName("v")
    private final String version;

    @SerializedName("https")
    private final String https;

    @SerializedName("lang")
    private final String language;

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("device_id")
    private final String deviceId;

    @SerializedName("timestamp")
    private final String timestamp;

    public TokenRequestBody(TokenData token) {
        this.accessToken = token.getAccessToken();
        this.deviceId = token.getDeviceId();
        this.timestamp = "0";
        this.version = "5.93";
        this.https = "1";
        this.language = "en";
    }

    public String getVersion() {
        return version;
    }

    public String getHttps() {
        return https;
    }

    public String getLanguage() {
        return language;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
