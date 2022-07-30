package com.board.exception;

public class MemberDoNotUseOtherThingException extends RuntimeException{
    public MemberDoNotUseOtherThingException() {
    }

    public MemberDoNotUseOtherThingException(String message) {
        super(message);
    }

    public MemberDoNotUseOtherThingException(String message, Throwable cause) {
        super(message, cause);
    }
}
