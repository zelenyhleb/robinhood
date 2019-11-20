package ru.krivocraft.robinhood.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signature {

    private final String encoded;

    public Signature(String string) {

        String encoded = string;
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            encoded = getHexString((digest.digest((string).getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.encoded = encoded;
    }

    private String getHexString(byte[] messageDigest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            if ((0xff & b) < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(0xff & b));
        }
        return sb.toString();
    }

    public String getEncoded() {
        return encoded;
    }
}
