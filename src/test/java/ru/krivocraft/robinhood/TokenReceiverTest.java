package ru.krivocraft.robinhood;

import org.junit.Test;

import java.io.IOException;

public class TokenReceiverTest {

    @Test
    public void getTokenTest() {
        TokenReceiver tokenReceiver = new TokenReceiver();
        try {
            System.out.println(tokenReceiver.refreshToken(tokenReceiver.getInitialToken("nikifor.fedorov@gmail.com", "QamaziK2550")).getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
