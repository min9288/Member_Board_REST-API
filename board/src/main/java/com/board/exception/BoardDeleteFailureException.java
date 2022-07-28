package com.board.exception;

public class BoardDeleteFailureException extends RuntimeException{

        public BoardDeleteFailureException() {}

        public BoardDeleteFailureException(String message) {
                super(message);
        }

        public BoardDeleteFailureException(String message, Throwable cause) {
                super(message, cause);
        }
}
