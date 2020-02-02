package ru.krivocraft.robinhood;

import org.junit.Test;
import ru.krivocraft.robinhood.network.M3U8Link;

public class DecoderTest {

    @Test
    public void decoderTest() {
        System.out.println(new M3U8Link().decode("https://cs9-7v4.vkuseraudio.net/p16/59bd74a560b/b02afc1b07c0dc/index.m3u8?extra=w4zhUOaYxRVVYH5OvdX4kCaUN6JFsAjOM-n1DUuPNubHTJG_kQe28a8oS--cgMVkzpPIC09pSoM8hKe5fOqJyzC1JYfeZ_VFuFCgak9BC1qJ-dwNoLXVw24PyEoC91U89AwTs5-wi6w9avL1i0c1Vw"));
    }
}
