package com.example.App.security;

import com.example.App.model.UserInfo;

public class SecurityContext {

        //Lagrar data specifik för tråd. Håller användarinfo per tråd.
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    //Sätter in UserInfo-objektet i ThreadLocal för nuvarande tråd
    public static void putInThreadLocal(UserInfo loginCredentials) {
        threadLocal.set(loginCredentials);
    }

     //Tar bort UserInfo-objektet från ThreadLocal för att undvika minnesläckor
    public static UserInfo getThreadLocal() {
        return threadLocal.get();
    }

      //Tar bort UserInfo-objektet från ThreadLocal för att undvika minnesläckor
    protected static void clear() {
        threadLocal.remove();
    }

     //Rensar ThreadLocal automatiskt när klar med säkerhetskontexten
    public record SecurityContextCloseable() implements AutoCloseable {

          // Lagrar UserInfo i ThreadLocal, vidarebefordrar till SecurityContext
        public void putInThreadLocal(UserInfo loginCredentials) {
            SecurityContext.putInThreadLocal(loginCredentials);
        }

           //Rensas automatiskt när blocket avslutas (try-with-resources)
        @Override
        public void close() {
            SecurityContext.clear();
        }

    }
}
