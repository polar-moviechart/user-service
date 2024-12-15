package com.polar_moviechart.userservice.domain.service.movie.dtos;

import com.polar_moviechart.userservice.domain.entity.movie.MovieReview;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateReviewRes {
    private String nickname;
    private Long reviewId;
    private String content;
    private LocalDateTime modifiedAt;

    @Builder
    public UpdateReviewRes(String nickname, Long reviewId, String content, LocalDateTime modifiedAt) {
        this.nickname = nickname;
        this.reviewId = reviewId;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateReviewRes from(MovieReview movieReview) {
        return UpdateReviewRes.builder()
                .nickname(movieReview.getNickname())
                .reviewId(movieReview.getId())
                .content(movieReview.getContent())
                .modifiedAt(movieReview.getModifiedAt())
                .build();
    }
}
