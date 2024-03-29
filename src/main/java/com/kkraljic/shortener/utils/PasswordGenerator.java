package com.kkraljic.shortener.utils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final Random RANDOM = new SecureRandom();

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int PASSWORD_LENGTH = 8;

    public static String generatePassword() {

        final StringBuilder returnValue = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);

    }

}
