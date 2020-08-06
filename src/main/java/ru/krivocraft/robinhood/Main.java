package ru.krivocraft.robinhood;

import ru.krivocraft.robinhood.api.Robinhood;
import ru.krivocraft.robinhood.api.TokenException;
import ru.krivocraft.robinhood.model.Audio;
import ru.krivocraft.robinhood.model.Token;
import ru.krivocraft.robinhood.network.M3U8Link;
import ru.krivocraft.robinhood.storage.Storage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage() {
            private Token token;

            @Override
            public void putToken(Token token) {
                this.token = token;
            }

            @Override
            public Token getToken() {
                return token;
            }

            @Override
            public boolean available() {
                return token != null;
            }
        };
        Robinhood robinhood = new Robinhood(storage);
        while (!exit) {
            switch (scanner.nextInt()) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    List<Audio> audioList = new LinkedList<>();

                    try {
                        audioList.addAll(robinhood.tryWithCached());
                    } catch (TokenException e) {
                        System.out.println("No token provided");
                        String username = scanner.next();
                        String password = scanner.next();
                        try {
                            audioList.addAll(robinhood.tryWithNewToken(username, password, ""));
                        } catch (TokenException tokenException) {
                            System.out.println("Need validation");
                            try {
                                audioList.addAll(robinhood.tryWithNewToken(username, password, scanner.next()));
                            } catch (TokenException exception) {
                                exception.printStackTrace();
                            }
                        }

                    }
                    for (Audio audio : audioList) {
                        System.out.println(M3U8Link.decode(audio.getUrl()));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
