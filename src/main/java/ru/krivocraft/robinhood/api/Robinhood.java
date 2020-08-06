package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.krivocraft.robinhood.api.TokenException;
import ru.krivocraft.robinhood.api.TokenReceiver;
import ru.krivocraft.robinhood.model.*;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.VKResponse;
import ru.krivocraft.robinhood.network.requests.VKRequest;
import ru.krivocraft.robinhood.network.requests.GetUserAudioRequest;
import ru.krivocraft.robinhood.network.requests.GetIdentifierRequest;
import ru.krivocraft.robinhood.storage.Storage;

import java.io.IOException;
import java.util.List;

public class Robinhood {

    private final Storage storage;
    private final Client client;
    private final TokenReceiver receiver;

    public Robinhood(Storage storage) {
        this.storage = storage;
        this.receiver = new TokenReceiver();
        this.client = new Client();
    }

    public List<Audio> tryWithCached() throws TokenException, IOException {
        if (storage.available()) {
            VKRequest userRequest = new GetIdentifierRequest(storage.getToken());
            VKResponse<User> userResponse = new Gson().fromJson(new ApiInterface().send(userRequest), new TypeToken<VKResponse<User>>() {
            }.getType());
            VKRequest musicRequest = new GetUserAudioRequest(String.valueOf(userResponse.getResponse().getIdentifier()), storage.getToken());
            VKResponse<AudioList> response = new Gson().fromJson(new ApiInterface().send(musicRequest), new TypeToken<VKResponse<AudioList>>() {
            }.getType());
            return response.getResponse().getItems();
        } else {
            System.out.println("No token provided");
            throw new TokenException("No token provided");
        }
    }

    public List<Audio> tryWithNewToken(String username, String password, String code) throws IOException, TokenException {
        Token initialToken = receiver.getInitialToken(username, password, code);
        String secret = initialToken.getSecret();
        String accessToken = receiver.refreshToken(initialToken).getAccessToken();
        Token token = new Token(accessToken, secret, client.getClientId());
        storage.putToken(token);

        return tryWithCached();
    }

}
