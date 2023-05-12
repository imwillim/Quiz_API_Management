package com.example.quiz_api_management.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean matches(String password, String encodedPassword){
        return passwordEncoder.matches(password, encodedPassword);
    }
}
