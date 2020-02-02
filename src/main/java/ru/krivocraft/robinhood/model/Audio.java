package ru.krivocraft.robinhood.model;

public class Audio {
    private final String url;
    private final String title;
    private final String artist;
    private final int duration;

    public Audio(String url, String title, String artist, int duration) {
        this.url = url;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }


    public String getUrl() {
        return url;
    }
}
