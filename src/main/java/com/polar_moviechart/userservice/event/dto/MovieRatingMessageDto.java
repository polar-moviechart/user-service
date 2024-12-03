package com.polar_moviechart.userservice.event.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MovieRatingMessageDto {
    private Long userId;
    private Integer code;
    private Double value;
    private UserActivityType type;

    @Builder
    public MovieRatingMessageDto(Long userId, Integer code, Double value, UserActivityType type) {
        this.userId = userId;
        this.code = code;
        this.value = value;
        this.type = type;
    }

    public static MovieRatingMessageDto from(Long userId, Integer code, Double value) {
        return MovieRatingMessageDto.builder()
                .userId(userId)
                .code(code)
                .value(value)
                .type(UserActivityType.RATING)
                .build();
    }
}
