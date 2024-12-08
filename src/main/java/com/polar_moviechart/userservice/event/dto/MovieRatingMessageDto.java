package com.polar_moviechart.userservice.event.dto;

import com.polar_moviechart.userservice.domain.service.movie.dtos.UpdateRatingRes;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MovieRatingMessageDto {
    private Long userId;
    private Integer code;
    private Boolean isNew;
    private Double value;
    private Double oldValue;
    private UserActivityType type;

    @Builder
    public MovieRatingMessageDto(Long userId, Integer code, Boolean isNew, Double value, Double oldValue, UserActivityType type) {
        this.userId = userId;
        this.code = code;
        this.isNew = isNew;
        this.value = value;
        this.oldValue = oldValue;
        this.type = type;
    }

    public static MovieRatingMessageDto from(Long userId, Integer code, UpdateRatingRes updateRatingRes) {
        return MovieRatingMessageDto.builder()
                .userId(userId)
                .code(code)
                .isNew(updateRatingRes.isNew())
                .value(updateRatingRes.getRating())
                .oldValue(updateRatingRes.getOldRating())
                .type(UserActivityType.RATING)
                .build();
    }
}
