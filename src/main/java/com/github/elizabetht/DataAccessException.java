package com.github.elizabetht;

public class DataAccessException extends Exception {

    public DataAccessException(String message, Throwable exception) {
        super(message, exception);
    }
}
