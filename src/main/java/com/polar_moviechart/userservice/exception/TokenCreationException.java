package com.polar_moviechart.userservice.exception;

public class TokenCreationException extends RuntimeException {
    private static final String msg = "토큰 발행 중 문제가 발생했습니다.";

    public TokenCreationException() {
        super(msg);
    }

    public TokenCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenCreationException(String message) {
        super(message);
    }

    public TokenCreationException(Throwable cause) {
        super(msg, cause);
    }
}
