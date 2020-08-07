package ru.krivocraft.robinhood.api;

public class NoTokenException extends Exception {

    public NoTokenException() {
        super("No token provided");
    }

}
