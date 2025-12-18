package com.isvaso.exception;

public class FileManagerException extends BaseException {

    public FileManagerException(Throwable cause) {
        super(cause);
    }

    public FileManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileManagerException(String message, Object... args) {
        super(message, args);
    }
}
