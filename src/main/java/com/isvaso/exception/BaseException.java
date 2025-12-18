package com.isvaso.exception;

/**
 * BaseException is a custom exception that supports formatted messages.
 * <p>
 * This exception allows for message formatting using arguments, making it more
 * flexible and informative when used throughout the application.
 *
 * @author Biryukov_IS
 */
public class BaseException extends Exception {

    public BaseException(Throwable cause) {

        super(cause);
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Object... args) {
        super(message.formatted(args));
    }

}
