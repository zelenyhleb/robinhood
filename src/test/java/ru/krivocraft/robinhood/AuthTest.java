package ru.krivocraft.robinhood;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class AuthTest {

    @Test
    public void getAccessTokenTest() {
        AppAuthService service = new AppAuthService();
        service.getAuthApi().getAccessToken().enqueue(new Callback<TokenDataSet>() {
            @Override
            public void onResponse(Call<TokenDataSet> call, Response<TokenDataSet> response) {
                assertEquals(new TokenDataSet("8c9e28d18c9e28d18cc37419ab8cbc9a0288c9e8c9e28d1d14ce5953358bfdab42937ed", "0"), response.body());
            }

            @Override
            public void onFailure(Call<TokenDataSet> call, Throwable t) {
                assertEquals("1", "0");
            }
        });
    }
}
