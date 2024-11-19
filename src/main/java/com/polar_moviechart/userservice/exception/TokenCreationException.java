package com.polar_moviechart.userservice.exception;

public class TokenCreationException extends RuntimeException {
    private static final String msg = "토큰 발행 중 문제가 발생했습니다.";
    private ErrorInfo errorInfo = ErrorInfo.DEFAULT_ERROR;

    public TokenCreationException() {
        super(msg);
    }

    public TokenCreationException(ErrorInfo errorInfo, Throwable cause) {
        super(errorInfo.getMessage(), cause);
        this.errorInfo = errorInfo;
    }

    public TokenCreationException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }

    public TokenCreationException(Throwable cause) {
        super(msg, cause);
    }
}
