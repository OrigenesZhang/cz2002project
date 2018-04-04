package com.ntu.scse;

public class InvalidInfoException extends Exception {
    public InvalidInfoException() {
        super("Exception: The input information is invalid!");
    }

    public InvalidInfoException(String message) {
        super("Exception: The input information is invalid! :" + message);
    }
}
