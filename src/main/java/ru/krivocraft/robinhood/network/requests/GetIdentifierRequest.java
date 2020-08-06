package ru.krivocraft.robinhood.network.requests;

import ru.krivocraft.robinhood.hash.Signature;
import ru.krivocraft.robinhood.model.Token;

import java.util.HashMap;
import java.util.Map;

public class GetIdentifierRequest extends VKRequest {

    private final Token token;

    public GetIdentifierRequest(Token token) {
        this.token = token;
    }

    @Override
    protected Map<String, String> params() {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", token.getAccessToken());
        params.put("device_id", token.getDeviceId());
        params.put("v", "5.93");
        params.put("https", "1");
        params.put("lang", "en");
        params.put("sig", new Signature(query(params) + token.getSecret()).toString());
        return params;
    }

    @Override
    protected String method() {
        return "account.getProfileInfo";
    }
}
