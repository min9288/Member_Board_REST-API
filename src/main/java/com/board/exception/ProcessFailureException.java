package com.board.exception;

public class ProcessFailureException extends RuntimeException{
    public ProcessFailureException() {
    }

    public ProcessFailureException(String message) {
        super(message);
    }

    public ProcessFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
