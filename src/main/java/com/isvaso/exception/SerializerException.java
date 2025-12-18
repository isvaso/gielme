package com.isvaso.exception;

public class SerializerException extends BaseException {

    public SerializerException(Throwable cause) {
        super(cause);
    }

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializerException(String message, Object... args) {
        super(message, args);
    }
}