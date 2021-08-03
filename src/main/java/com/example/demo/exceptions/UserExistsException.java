package com.example.demo.exceptions;

public class UserExistsException extends Exception {
    private final String id;
    private final String name;
    private static final String EXCEPTION_MESSAGE = "User already exists with name %s and id %s";

    public UserExistsException(String id, String name) {
        super(String.format(EXCEPTION_MESSAGE, name, id));
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
