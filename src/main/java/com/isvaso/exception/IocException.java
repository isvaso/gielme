package com.isvaso.exception;

public class IocException extends BaseRuntimeException {

    public IocException(String message, Throwable cause) {
        super(message, cause);
    }

    public IocException(String message, Object... args) {
        super(message, args);
    }
}