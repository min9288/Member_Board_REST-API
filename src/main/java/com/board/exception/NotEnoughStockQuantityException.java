package com.board.exception;

public class NotEnoughStockQuantityException extends RuntimeException {
    public NotEnoughStockQuantityException() {
    }

    public NotEnoughStockQuantityException(String message) {
        super(message);
    }

    public NotEnoughStockQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
