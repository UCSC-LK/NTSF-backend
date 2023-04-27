package com.cops.ntsf.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHashUtil {
    public static String hashingPassword(String password) throws Exception {
        System.out.println("Original String to hash: " + password);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Hash: " + encoded);
        return encoded;
    }
}
