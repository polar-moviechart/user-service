package com.polar_moviechart.userservice.domain.dto;

public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer movieCode;
    private boolean like;

    @Override
    public String getType() {
        return "MOVIE_LIKE";
    }
}