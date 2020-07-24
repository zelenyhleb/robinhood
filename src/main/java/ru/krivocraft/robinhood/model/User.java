package ru.krivocraft.robinhood.model;

public class User {
    private final String username;
    private final String password;
    private final int identifier;

    public User(String username, String password, int identifier) {
        this.username = username;
        this.password = password;
        this.identifier = identifier;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getIdentifier() {
        return identifier;
    }
}
