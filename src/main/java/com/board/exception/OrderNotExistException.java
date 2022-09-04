package com.board.exception;

public class OrderNotExistException extends RuntimeException {
    public OrderNotExistException() {
    }

    public OrderNotExistException(String message) {
        super(message);
    }

    public OrderNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
