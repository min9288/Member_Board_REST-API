package com.board.exception;

public class AssetsNotEnoughException extends RuntimeException {
    public AssetsNotEnoughException() {
    }

    public AssetsNotEnoughException(String message) {
        super(message);
    }

    public AssetsNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }
}
