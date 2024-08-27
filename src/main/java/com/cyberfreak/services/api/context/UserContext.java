package com.cyberfreak.services.api.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private static final ThreadLocal<String> currentUserId = new ThreadLocal<>();

    public static void setCurrentUserId(String userId) {
        currentUserId.set(userId);
    }

    public static String getCurrentUserId() {
        return currentUserId.get();
    }

    public static void clear() {
        currentUserId.remove();
    }
}
