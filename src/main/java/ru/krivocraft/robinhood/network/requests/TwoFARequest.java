package ru.krivocraft.robinhood.network.requests;

import java.util.HashMap;
import java.util.Map;

public class TwoFARequest extends VKRequest{

    private final String validationSid;

    public TwoFARequest(String validationSid) {
        this.validationSid = validationSid;
    }

    @Override
    protected Map<String, String> params() {
        Map<String, String> params = new HashMap<>();
        params.put("sid", validationSid);
        params.put("v", "5.93");
        return params;
    }

    @Override
    protected String method() {
        return "auth.validatePhone";
    }
}
