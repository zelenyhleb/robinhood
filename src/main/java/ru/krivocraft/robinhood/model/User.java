package ru.krivocraft.robinhood.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("first_name")
    private final String firstName;

    @SerializedName("last_name")
    private final String lastName;

    @SerializedName("id")
    private final int identifier;

    public User(String firstName, String lastName, int identifier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getIdentifier() {
        return identifier;
    }
}
