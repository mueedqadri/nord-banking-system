package com.nord.service;

public class Context {
    private static int loggedInUserId;

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int loggedInUserId) {
        Context.loggedInUserId = loggedInUserId;
    }
}
