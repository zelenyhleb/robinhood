package ru.krivocraft.robinhood.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ApiInterface {

    private final OkHttpClient client;

    public ApiInterface() {
        client = new OkHttpClient();
    }

    public String sendRequest(ApiRequest apiRequest) throws IOException {
        final Request request = new Request.Builder()
                .url("https://api.vk.com/" + apiRequest.buildHttpQuery())
                .header("User-Agent", "VKAndroidApp/5.23-2978 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en; 320x240)")
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
        }
        return "";
    }
}
