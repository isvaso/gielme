package com.isvaso.exception;

public class RepositoryException extends BaseException {

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(String message, Object... args) {
        super(message, args);
    }
}
