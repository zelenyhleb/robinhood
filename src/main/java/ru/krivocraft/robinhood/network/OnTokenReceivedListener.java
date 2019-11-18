package ru.krivocraft.robinhood.network;

import ru.krivocraft.robinhood.model.Token;

public interface OnTokenReceivedListener {
    void onTokenReceived(Token token);

    void onTokenReceiverFailure(String reason);
}
