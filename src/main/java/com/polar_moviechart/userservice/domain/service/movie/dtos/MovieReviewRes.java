package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MovieReviewRes implements UserActionRes {
    private Long id;
    private Long userId;
    private String nickname;
    private Integer code;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public MovieReviewRes(Long id, Long userId, String nickname, Integer code, String content, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.code = code;
        this.content = content;
        this.createdAt = createdAt;
    }

    private static MovieReviewRes from(MovieReview movieReview) {
        return MovieReviewRes.builder()
                .id(movieReview.getId())
                .userId(movieReview.getUserId())
                .nickname(movieReview.getNickname())
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

    public void setTitle(String title) {
        this.title = title;
    }
}
