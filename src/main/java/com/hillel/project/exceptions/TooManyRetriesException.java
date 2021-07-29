package com.hillel.project.exceptions;

public class TooManyRetriesException extends RuntimeException {
    public TooManyRetriesException() {
        super();
    }

    public TooManyRetriesException(String message) {
        super(message);
    }

    public TooManyRetriesException(String message, Throwable cause) {
        super(message, cause);
    }
}
