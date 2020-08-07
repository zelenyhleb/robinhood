package ru.krivocraft.robinhood;

import ru.krivocraft.robinhood.api.CodeRequiredException;
import ru.krivocraft.robinhood.api.InvalidClientException;
import ru.krivocraft.robinhood.api.Robinhood;
import ru.krivocraft.robinhood.api.NoTokenException;
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
            System.out.println("enter 0 to exit, 1 to start");
            switch (scanner.nextInt()) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    List<Audio> audioList = new LinkedList<>();
                    attempt(scanner, robinhood, audioList);
                    for (Audio audio : audioList) {
                        System.out.println(M3U8Link.decode(audio.getUrl()));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static void attempt(Scanner scanner, Robinhood robinhood, List<Audio> audioList) throws IOException {
        try {
            audioList.addAll(robinhood.tryWithCached());
        } catch (NoTokenException e) {
            System.out.println("enter login and password");
            String username = scanner.next();
            String password = scanner.next();
            try {
                audioList.addAll(robinhood.tryWithNewToken(username, password, ""));
            } catch (NoTokenException noTokenException) {
                // Still no token after login, unreachable
            } catch (CodeRequiredException codeRequiredException) {
                System.out.println("enter code");
                String code = scanner.next();
                try {
                    audioList.addAll(robinhood.tryWithNewToken(username, password, code));
                } catch (NoTokenException noTokenException) {
                    // Still no token after login, unreachable
                } catch (CodeRequiredException | InvalidClientException requiredException) {
                    // Incorrect code or login data, try to log in again
                    attempt(scanner, robinhood, audioList);
                }
            } catch (InvalidClientException invalidClientException) {
                // Incorrect login data, try to log in again
                attempt(scanner, robinhood, audioList);
            }

        }
    }
}
