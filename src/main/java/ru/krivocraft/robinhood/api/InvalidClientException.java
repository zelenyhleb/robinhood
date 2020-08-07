package ru.krivocraft.robinhood.api;

public class InvalidClientException extends Exception {

    public InvalidClientException() {
        super("invalid login or password");
    }
}
