package com.polar_moviechart.userservice.utils;

import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    private Boolean isSuccess = true;
    private String errorMsg = null;
    private String code = null;
    private T data;

    public CustomResponse(T data) {
        this.data = data;
    }

    public CustomResponse(ErrorCode errorCode) {
        this.errorMsg = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.isSuccess = false;
    }

    public CustomResponse(ErrorInfo errorInfo) {
        this.errorMsg = errorInfo.getMessage();
        this.code = errorInfo.getCode();
        this.isSuccess = false;
    }

    public void setCode(ErrorCode errorCode) {
        this.errorMsg = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.isSuccess = false;
    }

    public void setInfo(ErrorInfo errorInfo) {
        this.errorMsg = errorInfo.getMessage();
        this.code = errorInfo.getCode();
        this.isSuccess = false;
    }

    public void setCode(String errorCode) {
        this.code = errorCode;
    }
}
