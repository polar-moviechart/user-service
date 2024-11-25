package com.polar_moviechart.userservice.exception;

public class UserBusinessException extends RuntimeException {
    private static final String msg = "서비스 처리 중 오류가 발생했습니다..";
    private ErrorCode errorCode = ErrorCode.DEFAULT_ERROR;

    public UserBusinessException() {
        super(msg);
    }

    public UserBusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public UserBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public UserBusinessException(Throwable cause) {
        super(msg, cause);
    }

    public String getCode() {
        return this.errorCode.getCode();
    }
}
