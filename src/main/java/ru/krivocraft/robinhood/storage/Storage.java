package ru.krivocraft.robinhood.storage;

import ru.krivocraft.robinhood.model.Token;

public interface Storage {

    void putToken(Token token);

    Token getToken();

}
