package ru.krivocraft.robinhood.network;

public class M3U8Link {

    public static String decode(String url) {
        if (url.contains("index.m3u8?")) {
            String[] urlParts = url.split("/");
            if (url.contains("/audios/")) {
                return "https://" +
                        urlParts[2] + "/" +
                        urlParts[3] + "/" +
                        urlParts[4] + "/" +
                        urlParts[6] + "/" +
                        urlParts[7] + ".mp3";
            } else {
                return "https://" +
                        urlParts[2] + "/" +
                        urlParts[3] + "/" +
                        urlParts[5] + ".mp3";
            }
        } else {
            return url;
        }
    }
}
