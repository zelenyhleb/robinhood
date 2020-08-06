package ru.krivocraft.robinhood.network.requests;

import java.util.Map;

public abstract class VKRequest {

    public String query(Map<String, String> params) {
        StringBuilder builder = new StringBuilder("/method/" + method() + "?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public String query() {
        return query(params());
    }

    protected abstract Map<String, String> params();

    protected abstract String method();

}
