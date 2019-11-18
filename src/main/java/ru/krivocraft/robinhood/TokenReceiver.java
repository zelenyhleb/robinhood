package ru.krivocraft.robinhood;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.network.*;
import ru.krivocraft.robinhood.network.datasets.TokenDataSet;

import java.util.Random;

public class TokenReceiver {

    private void getNonRefreshed(String username, String password, OnTokenReceivedListener listener) {
        final String deviceId = randomString(16, "0123456789abcdef");
        final AuthApi authApi = new AuthService().getAuthApi();

        authApi.getAccessToken(deviceId, username, password).enqueue(new Callback<TokenDataSet>() {
            @Override
            public void onResponse(Call<TokenDataSet> call, Response<TokenDataSet> response) {
                TokenDataSet body = response.body();
                if (body != null) {
                    if (body.getError() == null) {
                        listener.onTokenReceived(new Token(body.getAccessToken(), body.getSecret(), deviceId));
                    } else {
                        listener.onTokenReceiverFailure(body.getError());
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenDataSet> call, Throwable t) {
                listener.onTokenReceiverFailure("failure");
            }
        });
    }

    private void refreshToken(Token token) {
        final VkApi vkApi = new VkApiService().getVkApi();



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
