package ru.krivocraft.robinhood;

import com.google.gson.Gson;
import ru.krivocraft.robinhood.api.TokenReceiver;
import ru.krivocraft.robinhood.model.Audio;
import ru.krivocraft.robinhood.model.Client;
import ru.krivocraft.robinhood.model.Response;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.requests.ApiRequest;
import ru.krivocraft.robinhood.network.requests.AudioGet;
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
            ApiRequest musicRequest = new AudioGet().getAudioRequest("audio.get", "33143959", storage.getToken());
            Response response = new Gson().fromJson(new ApiInterface().sendRequest(musicRequest), Response.class);
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
