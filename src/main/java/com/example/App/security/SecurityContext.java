package com.example.App.security;

import com.example.App.model.UserInfo;

public class SecurityContext {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static void putInThreadLocal(UserInfo loginCredentials) {
        threadLocal.set(loginCredentials);
    }

    public static UserInfo getThreadLocal() {
        return threadLocal.get();
    }

    protected static void clear() {
        threadLocal.remove();
    }

    public record SecurityContextCloseable() implements AutoCloseable {

        public void putInThreadLocal(UserInfo loginCredentials) {
            SecurityContext.putInThreadLocal(loginCredentials);
        }

        @Override
        public void close() {
            SecurityContext.clear();
        }

    }
}
