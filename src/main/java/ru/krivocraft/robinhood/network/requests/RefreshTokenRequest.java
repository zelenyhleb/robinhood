package ru.krivocraft.robinhood.network.requests;

import ru.krivocraft.robinhood.hash.Signature;
import ru.krivocraft.robinhood.model.TokenData;

import java.util.HashMap;
import java.util.Map;

public class RefreshTokenRequest {


    public ApiRequest getTokenRequest(TokenData tokenData) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", tokenData.getAccessToken());
        params.put("device_id", tokenData.getDeviceId());
        params.put("v", "5.93");
        params.put("https", "1");
        params.put("lang", "en");
        params.put("sig", new Signature(buildHttpQuery(params, "auth.refreshToken") + tokenData.getSecret()).toString());
        return new ApiRequest("auth.refreshToken", params);
    }

    public String buildHttpQuery(Map<String, String> params, String method) {
        StringBuilder builder = new StringBuilder("/method/" + method + "?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}