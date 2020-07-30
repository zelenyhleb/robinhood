package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import org.junit.Test;
import ru.krivocraft.robinhood.model.*;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.requests.ApiRequest;
import ru.krivocraft.robinhood.network.requests.AudioGet;
import ru.krivocraft.robinhood.network.M3U8Link;
import ru.krivocraft.robinhood.storage.Storage;

import java.io.IOException;

public class AudioApiTest {

    private final Storage storage = new Storage() {
        private Token token;

        @Override
        public void putToken(Token token) {
            this.token = token;
        }

        @Override
        public Token getToken() {
            return token;
        }

        @Override
        public boolean available() {
            return token != null;
        }
    };

    @Test
    public void getAudio() throws IOException {
        TokenReceiver tokenReceiver = new TokenReceiver(storage);
        Client client = new Client();
        Token initialToken = tokenReceiver.getInitialToken("kefir.fedorov@gmail.com", "QamaziK2550", "");
        String secret = initialToken.getSecret();
        String token = tokenReceiver.refreshToken(initialToken).getAccessToken();
        ApiRequest musicRequest = new AudioGet().getAudioRequest("audio.get", "33143959",
                new Token(token, secret, client.getClientId()));
        Response response = new Gson().fromJson(new ApiInterface().sendRequest(musicRequest), Response.class);
        for (Audio audio : response.getResponse().getItems()) {
            System.out.println(M3U8Link.decode(audio.getUrl()));
        }
    }
}
