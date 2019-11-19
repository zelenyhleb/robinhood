package ru.krivocraft.robinhood.model;

import com.google.gson.annotations.SerializedName;

public class Token {

    @SerializedName("token")
    private final String token;

    public static final Token EMPTY = new Token("");

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
