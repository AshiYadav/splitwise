package com.ashish.splitwise.exception;

public class InvalidEmailExpception extends RuntimeException{
    public InvalidEmailExpception() {
    }

    public InvalidEmailExpception(String message) {
        super(message);
    }

    public InvalidEmailExpception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailExpception(Throwable cause) {
        super(cause);
    }

    public InvalidEmailExpception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
