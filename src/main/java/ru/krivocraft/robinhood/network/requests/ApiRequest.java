package ru.krivocraft.robinhood.network.requests;

import java.util.Map;

public class ApiRequest {

    private final String method;
    private final Map<String, String> params;

    public ApiRequest(String method, Map<String, String> params) {
        this.method = method;
        this.params = params;
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
