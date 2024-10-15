package com.itbh.hevent.exceptions;

public class EmailSMTPException extends RuntimeException {
    public EmailSMTPException(String message, Throwable cause) {
        super(message, cause);
    }
}
