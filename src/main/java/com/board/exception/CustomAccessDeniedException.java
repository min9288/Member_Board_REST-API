package com.board.exception;

public class CustomAccessDeniedException extends RuntimeException{

    public CustomAccessDeniedException() {}

    public CustomAccessDeniedException(String message) {
        super(message);
    }

    public CustomAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
