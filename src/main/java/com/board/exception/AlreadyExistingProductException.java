package com.board.exception;

public class AlreadyExistingProductException extends RuntimeException {
    public AlreadyExistingProductException() {
    }

    public AlreadyExistingProductException(String message) {
        super(message);
    }

    public AlreadyExistingProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
