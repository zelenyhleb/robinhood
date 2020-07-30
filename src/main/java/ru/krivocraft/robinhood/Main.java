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
import ru.krivocraft.robinhood.network.M3U8Link;
import ru.krivocraft.robinhood.storage.Storage;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage() {
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
        TokenReceiver tokenReceiver = new TokenReceiver(storage);
        Client client = new Client();
        while (!exit) {
            switch (scanner.nextInt()) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    Token initialToken = tokenReceiver.getInitialToken("kefir.fedorov@gmail.com", "QamaziK2550", "");
                    String secret = initialToken.getSecret();
                    String token = tokenReceiver.refreshToken(initialToken).getAccessToken();
                    ApiRequest musicRequest = new AudioGet().getAudioRequest("audio.get", "33143959",
                            new Token(token, secret, client.getClientId()));
                    String json = new ApiInterface().sendRequest(musicRequest);
                    Response response = new Gson().fromJson(json, Response.class);
                    System.out.println("music_response " + json);
                    for (Audio audio : response.getResponse().getItems()) {
                        System.out.println(M3U8Link.decode(audio.getUrl()));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
