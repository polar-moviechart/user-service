package com.polar_moviechart.userservice.domain.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MovieReviewRes {
    private Long userId;
    private Integer code;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public MovieReviewRes(Long userId, Integer code, String content, LocalDateTime createdAt) {
        this.userId = userId;
        this.code = code;
        this.content = content;
        this.createdAt = createdAt;
    }
}
