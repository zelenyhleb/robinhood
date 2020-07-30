package ru.krivocraft.robinhood.vkshit;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class TokenResponse {

    @SerializedName("response")
    private final Map<String, String> response;

    public TokenResponse() {
        response = new HashMap<>();
    }

    public String getToken() {
        return response.get("token");
    }
}
