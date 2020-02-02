package ru.krivocraft.robinhood.network;

public class M3U8Link {

    public static String decode(String url) {
        if (url.contains("index.m3u8?")) {
            if (url.contains("/audios/")) {
                return url.replaceAll("(.+?)/[^/]+?/audios/([^/]+)/.+", "\\1/audios/\\2.mp3");
            } else {
                String[] urlParts = url.split("/");
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
