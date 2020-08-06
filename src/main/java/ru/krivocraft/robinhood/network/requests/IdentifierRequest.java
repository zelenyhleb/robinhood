package ru.krivocraft.robinhood.network.requests;

import ru.krivocraft.robinhood.hash.Signature;
import ru.krivocraft.robinhood.model.Token;

import java.util.HashMap;
import java.util.Map;

public class IdentifierRequest {

    public ApiRequest getIdentifierRequest(Token token) {
        String method = "account.getProfileInfo";
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token.getAccessToken());
        params.put("device_id", token.getDeviceId());
        params.put("v", "5.93");
        params.put("https", "1");
        params.put("lang", "en");
        params.put("sig", new Signature(buildHttpQuery(params, method) + token.getSecret()).toString());
        return new ApiRequest(method, params);
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
