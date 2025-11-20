package com.isvaso.exception;

public class MigrationException extends BaseException {

    public MigrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MigrationException(String message, Object... args) {
        super(message, args);
    }
}