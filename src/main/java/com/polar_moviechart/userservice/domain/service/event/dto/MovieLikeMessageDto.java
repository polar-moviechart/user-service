package com.polar_moviechart.userservice.domain.service.event.dto;

import lombok.Builder;

public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer movieCode;
    private Integer likeCnt;
    private MessageType type;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer movieCode, Integer likeCnt, MessageType type) {
        this.userId = userId;
        this.movieCode = movieCode;
        this.likeCnt = likeCnt;
        this.type = type;
    }
    @Override
    public String getType() {
        return "MOVIE_LIKE";
    }

    @Override
    public Integer getCode() {
        return this.movieCode;
    }

    @Override
    public Integer getValue() {
        return this.likeCnt;
    }
}