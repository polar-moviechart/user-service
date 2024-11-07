package com.polar_moviechart.userservice.exception;

import lombok.Getter;

@Getter
public enum ErrorInfo {

    DEFAULT_ERROR("9999", "예기치 못한 오류가 발생했습니다." + "\n" + "불편을 드려 죄송합니다."),
    TOKEN_CREATION_ERROR("100", "로그인 중 문제가 발생했습니다."),
    NOT_ALLOWED("104", "접근 권한이 없습니다.");

    private String code = "U101";
    private final String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ErrorInfo(String message) {
        this.message = message;
    }
}
