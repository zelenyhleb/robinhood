package ru.krivocraft.robinhood;

import org.junit.Test;
import ru.krivocraft.robinhood.network.M3U8Link;

public class DecoderTest {

    @Test
    public void decoderTest() {
        System.out.println(M3U8Link.decode("https://psv4.vkuseraudio.net/c613721/u33143959/5956c1e2e0e/audios/e4818de57db9/index.m3u8?extra=of2SoLNVKV7rV4FaFYGvQc0cRFTQiNrP3CdS51s3ZETo4SEMJwpJ9JfMsTZKJ5ybEK893i5_z2UHhXzgP5d5hOslE74C9f9l-yXzhscLY9ZKSYm6Ip1kqvTYwvx2gdnzld79r7jSTj5W0mlp6p7OxA"));
    }
}
