package com.ashish.splitwise.exception;

public class InvalidUserSignupInputException extends RuntimeException{
    public InvalidUserSignupInputException() {
    }

    public InvalidUserSignupInputException(String message) {
        super(message);
    }

    public InvalidUserSignupInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserSignupInputException(Throwable cause) {
        super(cause);
    }

    public InvalidUserSignupInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
