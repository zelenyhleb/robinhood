package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.TokenResultDataSet;
import ru.krivocraft.robinhood.network.VKResponse;
import ru.krivocraft.robinhood.network.requests.RefreshTokenRequest;
import ru.krivocraft.robinhood.network.requests.TwoFARequest;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class TokenReceiver {

    private final Gson gson = new Gson();
    private final ApiInterface apiInterface = new ApiInterface();

    public Token getInitialToken(String username, String password) throws IOException, NeedValidationException, InvalidClientException {
        OkHttpClient client = new OkHttpClient();
        final String deviceId = randomString();
        String url = "https://oauth.vk.com/token" +
                "?grant_type=password&client_id=2274003" +
                "&client_secret=hHbZxrka2uZ6jB1inYsH&lang=en" +
                "&scope=nohttps,all&username=" + username + "&password=" + password + "&device_id=" + deviceId;
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                TokenResultDataSet dataSet = gson.fromJson(body.string(), TokenResultDataSet.class);
                if ("need_validation".equals(dataSet.getError())) {
                    apiInterface.send(new TwoFARequest(dataSet.getValidationSid()));
                    throw new NeedValidationException();
                } else if ("invalid_client".equals(dataSet.getError())) {
                    throw new InvalidClientException();
                }
                return new Token(dataSet.getAccessToken(), dataSet.getSecret(), deviceId);
            } else {
                return new Token("", "", deviceId);
            }
        }
    }

    public Token getInitialToken(String username, String password, String code) throws IOException, InvalidClientException {
        OkHttpClient client = new OkHttpClient();
        final String deviceId = randomString();
        String url = "https://oauth.vk.com/token" +
                "?grant_type=password&client_id=2274003" +
                "&client_secret=hHbZxrka2uZ6jB1inYsH&lang=en" +
                "&scope=nohttps,all&username=" + username + "&password=" + password +
                "&2fa_supported=1&force_sms=1&code=" + code + "&device_id=" + deviceId;
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                TokenResultDataSet dataSet = gson.fromJson(body.string(), TokenResultDataSet.class);
                if ("invalid_client".equals(dataSet.getError())) {
                    throw new InvalidClientException();
                }
                return new Token(dataSet.getAccessToken(), dataSet.getSecret(), deviceId);
            } else {
                throw new IOException();
            }
        }
    }

    public Token refreshToken(Token token) throws IOException {
        VKResponse<Map<String, String>> response = gson.fromJson(
                apiInterface.send(
                        new RefreshTokenRequest(token)), new TypeToken<VKResponse<Map<String, String>>>() {
                }.getType());
        return new Token(response.getResponse().get("token"), token.getSecret(), token.getDeviceId());
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
