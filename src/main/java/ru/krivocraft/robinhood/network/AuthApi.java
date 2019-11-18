package ru.krivocraft.robinhood.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.krivocraft.robinhood.network.datasets.TokenDataSet;

public interface AuthApi {

    @GET("token?grant_type=password&client_id=2274003&client_secret=hHbZxrka2uZ6jB1inYsH&&lang=en&scope=nohttps,all")
    Call<TokenDataSet> getAccessToken(@Query("device_id") String deviceId,
                                      @Query("username") String username,
                                      @Query("password") String password);

}
