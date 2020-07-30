package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.requests.RefreshTokenRequest;
import ru.krivocraft.robinhood.network.TokenResultDataSet;
import ru.krivocraft.robinhood.network.requests.TwoFARequest;
import ru.krivocraft.robinhood.storage.Storage;
import ru.krivocraft.robinhood.vkshit.TokenResponse;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TokenReceiver {

    private final Gson gson = new Gson();
    private final ApiInterface apiInterface = new ApiInterface();
    private final Storage storage;

    public TokenReceiver(Storage storage) {
        this.storage = storage;
    }

    public Token getInitialToken(String username, String password, String code) throws IOException {
        if (!storage.available()) {
            OkHttpClient client = new OkHttpClient();
            final String deviceId = randomString();
            String url = "https://oauth.vk.com/token" +
                    "?grant_type=password&client_id=2274003" +
                    "&client_secret=hHbZxrka2uZ6jB1inYsH&lang=en" +
                    "&scope=nohttps,all&username=" + username + "&password=" + password +
                    (code.isEmpty() ? "" : "&2fa_supported=1&force_sms=1&code=" + code) + "&device_id=" + deviceId;
            final Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                ResponseBody body = response.body();
                if (body != null) {
                    TokenResultDataSet dataSet = gson.fromJson(body.string(), TokenResultDataSet.class);
                    if ("need_validation".equals(dataSet.getError())) {
                        Scanner scanner = new Scanner(System.in);
                        apiInterface.sendRequest(new TwoFARequest().get2FARequest(dataSet.getValidationSid()));
                        return getInitialToken(username, password, scanner.nextLine());
                    }
                    return new Token(dataSet.getAccessToken(), dataSet.getSecret(), deviceId);
                } else {
                    return new Token("", "", deviceId);
                }
            }
        } else {
            return storage.getToken();
        }
    }

    public Token refreshToken(Token token) throws IOException {
        TokenResponse response = gson.fromJson(
                apiInterface.sendRequest(
                        new RefreshTokenRequest().getTokenRequest(token)), TokenResponse.class);
        Token refreshed = new Token(response.getToken(), token.getSecret(), token.getDeviceId());
        storage.putToken(refreshed);
        return refreshed;
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
