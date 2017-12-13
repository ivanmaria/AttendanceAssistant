package com.github.ivanmaria.attendanceassistant;

/**
 * Created by IsaacIvan on 13-12-2017.
 */

public class User {

    private static String name, id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public static String getName() {
        return name;
    }

    public static String getId() {
        return id;
    }

}