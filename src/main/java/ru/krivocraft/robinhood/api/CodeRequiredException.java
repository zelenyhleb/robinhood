package ru.krivocraft.robinhood.api;

public class CodeRequiredException extends Exception {

    public CodeRequiredException() {
        super("code required");
    }
}
