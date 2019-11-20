package ru.krivocraft.robinhood.network;

import ru.krivocraft.robinhood.hash.Signature;
import ru.krivocraft.robinhood.model.TokenData;

import java.util.Map;

public class ApiRequest {

    private final String method;
    private final Map<String, String> params;
    private final TokenData tokenData;

    public ApiRequest(String method, Map<String, String> params, TokenData tokenData) {
        this.method = method;
        this.params = params;
        this.tokenData = tokenData;
        this.putDefaultParams();
    }

    private void putDefaultParams() {
        params.put("v", "5.93");
        params.put("lang", "en");
        params.put("https", "1");
        params.put("device_id", tokenData.getDeviceId());
        params.put("access_token", tokenData.getAccessToken());
        params.put("sig", new Signature(buildHttpQuery() + tokenData.getSecret()).toString());
    }

    public String buildHttpQuery() {
        StringBuilder builder = new StringBuilder("/method/" + getMethod() + "?");
        for (Map.Entry<String, String> entry : getParams().entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    String getMethod() {
        return method;
    }

    Map<String, String> getParams() {
        return params;
    }
}
