package com.isvaso.exception;

public class ControllerRegistryException extends BaseRuntimeException {

    public ControllerRegistryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerRegistryException(String message, Object... args) {
        super(message, args);
    }
}