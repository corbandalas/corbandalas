package com.corbandalas.web.security;

import org.springframework.stereotype.Service;


@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;

    public void loginSucceeded(String key) {
        LoginAttemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        LoginAttemptsCache.increaseAttempts(key);
    }

    public boolean isBlocked(String key) {
        try {
            return LoginAttemptsCache.getLoginAttempts(key) >= MAX_ATTEMPT;
        } catch (Exception e) {
            return false;
        }
    }
}