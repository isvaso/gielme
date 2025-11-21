package com.isvaso.exception;

public class RepositoryException extends BaseException {

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(String message, Object... args) {
        super(message, args);
    }
}
