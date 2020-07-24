package ru.krivocraft.robinhood.network.requests;

import java.util.HashMap;
import java.util.Map;

public class TwoFARequest {

    public ApiRequest get2FARequest(String validationSid) {
        Map<String, String> params = new HashMap<>();
        params.put("sid", validationSid);
        params.put("v", "5.93");
        return new ApiRequest("auth.validatePhone", params);
    }
}
