package ru.krivocraft.robinhood;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    @Test
    public void userAgent() {
        assertEquals("VKAndroidApp/5.23-2978 (Android 4.4.2; SDK 19; x86; unknown Android SDK built for x86; en; 320x240)",
                new Client().getUserAgent());
    }

    @Test
    public void userSecret() {
        assertEquals("hHbZxrka2uZ6jB1inYsH", new Client().getClientSecret());
    }

    @Test
    public void userId() {
        assertEquals("2274003", new Client().getClientId());
    }
}
