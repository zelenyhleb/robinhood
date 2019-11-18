package ru.krivocraft.robinhood.network;

public class AuthService extends NetworkService {

    private AuthApi authApi;

    public AuthService() {
        super("https://oauth.vk.com");
        authApi = getRetrofit().create(AuthApi.class);
    }

    public AuthApi getAuthApi() {
        return authApi;
    }
}
