package ru.krivocraft.robinhood.model;

import com.google.gson.annotations.SerializedName;

public class Audio {
    private final String url;
    private final String title;
    private final String artist;
    @SerializedName("duration")
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

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }
}
