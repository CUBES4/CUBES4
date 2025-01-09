package com.cubes4.CUBES4;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {
    public static void main(String[] args) {
        String rawPassword = "admin123";
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt(10));
        System.out.println("Hash généré : " + hashedPassword);
    }
}
