package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.model.TokenData;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.TokenGet;
import ru.krivocraft.robinhood.network.TokenResultDataSet;
import ru.krivocraft.robinhood.vkshit.TokenResponse;

import java.io.IOException;
import java.util.Random;

public class TokenReceiver {

    private final Gson gson = new Gson();
    private ApiInterface apiInterface = new ApiInterface();

    public TokenReceiver() {
    }

    TokenData getInitialToken(String username, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();
        final String deviceId = randomString();
        final Request request = new Request.Builder()
                .get()
                .url("https://oauth.vk.com/token" +
                        "?grant_type=password&client_id=2274003" +
                        "&client_secret=hHbZxrka2uZ6jB1inYsH&lang=en" +
                        "&scope=nohttps,all&username=" + username + "&password=" + password + "&device_id=" + deviceId)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                TokenResultDataSet dataSet = gson.fromJson(body.string(), TokenResultDataSet.class);
                return new TokenData(dataSet.getAccessToken(), dataSet.getSecret(), deviceId);
            } else {
                return new TokenData("", "", deviceId);
            }
        }
    }

    Token refreshToken(TokenData token) throws IOException {
        TokenResponse response = gson.fromJson(
                apiInterface.sendRequest(
                        new TokenGet().getTokenRequest("auth.refreshToken", token)), TokenResponse.class);
        return response.getToken();
    }

    private String randomString() {
        final int charactersLength = "0123456789abcdef".length();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            stringBuilder.append("0123456789abcdef".charAt(random.nextInt(charactersLength - 1)));
        }
        return stringBuilder.toString();
    }

}
