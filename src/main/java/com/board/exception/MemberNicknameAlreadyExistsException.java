package com.board.exception;

public class MemberNicknameAlreadyExistsException extends RuntimeException {
    public MemberNicknameAlreadyExistsException() {
    }

    public MemberNicknameAlreadyExistsException(String message) {
        super(message);
    }

    public MemberNicknameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
