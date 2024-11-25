package com.polar_moviechart.userservice.domain.entity.dto;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private static MovieReviewRes from(MovieReview movieReview) {
        return MovieReviewRes.builder()
                .userId(movieReview.getUserId())
                .code(movieReview.getCode())
                .content(movieReview.getContent())
                .createdAt(movieReview.getCreatedAt())
                .build();
    }

    public static List<MovieReviewRes> listFrom(List<MovieReview> movieReviews) {
        return movieReviews.stream()
                .map(MovieReviewRes::from)
                .collect(Collectors.toList());
    }
}
