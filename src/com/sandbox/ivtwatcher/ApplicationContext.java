package com.sandbox.ivtwatcher;

/**
 * Created with IntelliJ IDEA.
 * User: DKachurovskiy
 * Date: 5/20/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ApplicationContext {
    INSTANCE;

    private static final String s_server = "";
    private static final String s_database = "";
    private static final String s_username = "";
    private static final String s_password = "";

    public static String getServer() {
        return s_server;
    }

    public static String getDatabase() {
        return s_database;
    }

    public static String getUsername() {
        return s_username;
    }

    public static String getPassword() {
        return s_password;
    }
}
