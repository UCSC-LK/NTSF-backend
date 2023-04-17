package com.cops.ntsf.util;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHashUtil {
    public static String hashingPassword(String password) throws Exception {
        String originalString = password;
        System.out.println("Original String to hash: " + originalString);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalString.getBytes("UTF-8"));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Hash: " + encoded);
        return encoded;
    }
}
