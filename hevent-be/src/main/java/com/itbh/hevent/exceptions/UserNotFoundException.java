package com.itbh.hevent.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("User not found with username: " + username);
    }
}
