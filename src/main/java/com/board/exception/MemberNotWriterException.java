package com.board.exception;

public class MemberNotWriterException extends RuntimeException {

    public MemberNotWriterException() {
    }
    public MemberNotWriterException(String message) {
        super(message);
    }

    public MemberNotWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
