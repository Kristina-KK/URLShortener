package com.kkraljic.shortener.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hashPassword(final String password) {

        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // compute the hash of the password bytes
        final byte[] hash = md.digest(password.getBytes());

        // convert the hash bytes to a string of hexadecimal digits
        final StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();

    }

}
