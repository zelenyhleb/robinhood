package ru.krivocraft.robinhood.api;

public class NeedValidationException extends Exception {

    public NeedValidationException() {
        super("code required");
    }
}
