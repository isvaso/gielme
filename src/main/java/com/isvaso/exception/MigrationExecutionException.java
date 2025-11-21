package com.isvaso.exception;

public class MigrationExecutionException extends BaseException {

    public MigrationExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MigrationExecutionException(String message, Object... args) {
        super(message, args);
    }
}