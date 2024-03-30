package com.ashish.splitwise.exception;

public class UserWrongPasswordException extends RuntimeException{
    public UserWrongPasswordException() {
    }

    public UserWrongPasswordException(String message) {
        super(message);
    }

    public UserWrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserWrongPasswordException(Throwable cause) {
        super(cause);
    }

    public UserWrongPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
