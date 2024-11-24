package com.polar_moviechart.userservice.exception;

public class TokenCreationException extends RuntimeException {
    private static final String msg = "토큰 발행 중 문제가 발생했습니다.";
    private ErrorCode errorCode = ErrorCode.DEFAULT_ERROR;

    public TokenCreationException() {
        super(msg);
    }

    public TokenCreationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public TokenCreationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public TokenCreationException(Throwable cause) {
        super(msg, cause);
    }
}
