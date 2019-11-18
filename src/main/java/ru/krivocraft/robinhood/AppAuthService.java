package ru.krivocraft.robinhood;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class AppAuthService {
    private final Retrofit retrofit;

    public AppAuthService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://oauth.vk.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AuthApi getAuthApi() {
        return retrofit.create(AuthApi.class);
    }

    public interface AuthApi {

        @GET("/access_token?client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&v=5.23&grant_type=client_credentials")
        Call<TokenDataSet> getAccessToken();

    }
}
