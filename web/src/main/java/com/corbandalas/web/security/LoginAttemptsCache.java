package com.corbandalas.web.security;

import java.util.concurrent.ConcurrentHashMap;

public class LoginAttemptsCache {

    private final ConcurrentHashMap<String, Integer> attemptsCache;

    private static LoginAttemptsCache instance;

    private LoginAttemptsCache() {
        attemptsCache = new ConcurrentHashMap<>();
    }

    private static LoginAttemptsCache getInstance() {
        if (instance == null) {
            instance = new LoginAttemptsCache();
        }

        return instance;
    }

    public static Integer getLoginAttempts(String ip) {
        return getInstance().attemptsCache.get(ip);
    }

    public static void invalidate(String ip) {
        getInstance().attemptsCache.put(ip, 0);
    }

    public static void increaseAttempts(String ip) {

        var attempts = 0;

        try {
            attempts = getInstance().attemptsCache.get(ip);
        } catch (Exception e) {
        }

        getInstance().attemptsCache.put(ip, ++attempts);
    }

}
