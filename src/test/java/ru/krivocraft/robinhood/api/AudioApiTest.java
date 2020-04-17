package ru.krivocraft.robinhood.api;

import com.google.gson.Gson;
import org.junit.Test;
import ru.krivocraft.robinhood.model.Audio;
import ru.krivocraft.robinhood.model.Client;
import ru.krivocraft.robinhood.model.Response;
import ru.krivocraft.robinhood.model.TokenData;
import ru.krivocraft.robinhood.network.ApiInterface;
import ru.krivocraft.robinhood.network.ApiRequest;
import ru.krivocraft.robinhood.network.AudioGet;
import ru.krivocraft.robinhood.network.M3U8Link;

import java.io.IOException;

public class AudioApiTest {

    @Test
    public void getAudio() throws IOException {
        TokenReceiver tokenReceiver = new TokenReceiver();
        Client client = new Client();
        ApiInterface apiInterface = new ApiInterface();
        TokenData initialToken = tokenReceiver.getInitialToken("urUsername", "urPassword");
        String secret = initialToken.getSecret();
        String token = tokenReceiver.refreshToken(initialToken).getToken();
        ApiRequest musicRequest = new AudioGet().getAudioRequest("audio.get", "33143959",
                new TokenData(token, secret, client.getClientId()));
        Response response = new Gson().fromJson(apiInterface.sendRequest(musicRequest), Response.class);
        System.out.println(response.getResponse().getItems().size());
        for (Audio audio : response.getResponse().getItems()) {
            System.out.println(M3U8Link.decode(audio.getUrl()));
        }
    }
}
