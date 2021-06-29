package com.automation.utils.exceptions;

/**
 * @author Maheswara
 * @created on 28/06/21
 */
public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public APIException(String message) {
        super(message);
        if (message != null && message.contains("EMPTY")) {
            throw new AssertionError(message);
        }
    }

    public APIException(Throwable cause) {
        super(cause);

    }

    public APIException(String message, Throwable cause) {
        super(message, cause);

    }

    public APIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }
}
