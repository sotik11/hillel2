package com.hillel.project.exceptions;

public class IncorrectPassException extends Exception {
    public IncorrectPassException() {
        super();
    }

    public IncorrectPassException(String message) {
        super(message);
    }

    public IncorrectPassException(String message, Throwable cause) {
        super(message, cause);
    }
}
