package com.polar_moviechart.userservice.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MovieLikeMessageDto implements MessageDto {
    private Long userId;
    private Integer code;
    private Boolean value;
    private UserActivityType type;

    @Builder
    public MovieLikeMessageDto(Long userId, Integer code, Boolean value, UserActivityType type) {
        this.userId = userId;
        this.code = code;
        this.value = value;
        this.type = type;
    }
}