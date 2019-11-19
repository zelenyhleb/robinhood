package ru.krivocraft.robinhood;

import com.google.gson.Gson;
import okhttp3.*;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.model.TokenData;
import ru.krivocraft.robinhood.network.TokenRequestBody;
import ru.krivocraft.robinhood.network.TokenResultDataSet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenReceiver {

    private final OkHttpClient client;
    private final Gson gson = new Gson();

    public TokenReceiver() {
        client = new OkHttpClient();
    }

    TokenData getInitialToken(String username, String password) throws IOException {
        final String deviceId = randomString(16, "0123456789abcdef");
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
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final String httpQuery = buildHttpQuery(new TokenRequestBody(token));
            final Request request = new Request.Builder()
                    .url("https://api.vk.com/" + httpQuery +
                            "&sig=" + getHexString((digest.digest((httpQuery + token.getSecret()).getBytes(StandardCharsets.UTF_8)))))
                    .header("User-Agent", "VKAndroidApp/5.23-2978 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en; 320x240)")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                ResponseBody body = response.body();
                if (body != null) {
                    return gson.fromJson(body.string(), Token.class);
                }
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return Token.EMPTY;
    }

    private static String getHexString(byte[] messageDigest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            if ((0xff & b) < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(0xff & b));
        }
        return sb.toString();
    }

    private String buildHttpQuery(TokenRequestBody requestBody) {
        return "/method/auth.refreshToken?v=" + requestBody.getVersion() +
                "&https=" + requestBody.getHttps() +
                "&lang=" + requestBody.getLanguage() +
                "&access_token=" + requestBody.getAccessToken() +
                "&device_id=" + requestBody.getDeviceId() +
                "&timestamp=" + requestBody.getTimestamp();
    }

    private String randomString(int length, String characters) {
        final int charactersLength = characters.length();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(charactersLength - 1)));
        }
        return stringBuilder.toString();
    }

}
