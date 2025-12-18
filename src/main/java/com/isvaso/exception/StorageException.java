package com.isvaso.exception;

public class StorageException extends BaseException {

    public StorageException(Throwable cause) {
        super(cause);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(String message, Object... args) {
        super(message, args);
    }
}
