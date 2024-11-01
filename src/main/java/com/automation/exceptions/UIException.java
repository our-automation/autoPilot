package com.automation.exceptions;

public class UIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UIException(String message) {
        super(message);

    }

    public UIException(Throwable cause) {
        super(cause);

    }

    public UIException(String message, Throwable cause) {
        super(message, cause);

    }

    public UIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
