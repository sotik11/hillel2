package com.hillel.project.exceptions;

public class NotValidLoginException extends Exception{
    public NotValidLoginException() {
        super();
    }

    public NotValidLoginException(String message) {
        super(message);
    }

    public NotValidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
