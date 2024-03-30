package com.ashish.splitwise.exception;

public class InvalidPasswordExpception extends RuntimeException{
    public InvalidPasswordExpception() {
    }

    public InvalidPasswordExpception(String message) {
        super(message);
    }

    public InvalidPasswordExpception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordExpception(Throwable cause) {
        super(cause);
    }

    public InvalidPasswordExpception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
