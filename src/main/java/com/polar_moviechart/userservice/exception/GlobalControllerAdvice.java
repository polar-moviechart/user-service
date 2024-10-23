package com.polar_moviechart.userservice.exception;

import com.polar_moviechart.userservice.utils.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.polar_moviechart.userservice.domain.controller"})
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<CustomResponse<Object>> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        CustomResponse<Object> customResponse = new CustomResponse<>(null);
        customResponse.setIsSuccess(false);
        customResponse.setCode(ErrorCode.DEFAULT_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
    }

    @ExceptionHandler(value = {TokenCreationException.class})
    public ResponseEntity<CustomResponse<Object>> handleTokenCreationException(TokenCreationException e) {
        e.printStackTrace();
        CustomResponse<Object> customResponse = new CustomResponse<>(null);
        customResponse.setIsSuccess(false);
        customResponse.setCode(ErrorCode.TOKEN_CREATION_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
    }
}
