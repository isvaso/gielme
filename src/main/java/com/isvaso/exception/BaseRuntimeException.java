package com.isvaso.exception;

/**
 * BaseRuntimeException is a custom exception that supports formatted messages.
 * <p>
 * This exception allows for message formatting using arguments, making it more
 * flexible and informative when used throughout the application.
 *
 * @author Biryukov_IS
 */
public class BaseRuntimeException extends RuntimeException {

    public BaseRuntimeException(Throwable cause) {

        super(cause);
    }
    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRuntimeException(String message, Object... args) {
        super(message.formatted(args));
    }

}
