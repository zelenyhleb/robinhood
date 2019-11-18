package ru.krivocraft.robinhood;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.krivocraft.robinhood.network.AuthService;
import ru.krivocraft.robinhood.network.datasets.TokenDataSet;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AuthTest {

    @Test
    public void getAccessSuccessTokenTest() {
        AuthService service = new AuthService();
        service.getAuthApi().getAccessToken(randomString(16, "0123456789abcdef"), "nikifor.fedorov@gmail.com", "QamaziK2550").enqueue(new Callback<TokenDataSet>() {
            @Override
            public void onResponse(Call<TokenDataSet> call, Response<TokenDataSet> response) {
                if (response.body() != null) {
                    System.out.println(response.body().toString());
                } else {
                    fail();
                }
            }

            @Override
            public void onFailure(Call<TokenDataSet> call, Throwable t) {
                fail();
            }
        });
    }

    private String randomString(int length, String characters) {
        final int charactersLength = characters.length();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(random.nextInt(charactersLength - 1)));
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

}
