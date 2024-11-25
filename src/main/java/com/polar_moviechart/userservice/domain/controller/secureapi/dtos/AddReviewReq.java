package com.polar_moviechart.userservice.domain.controller.secureapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
public class AddReviewReq {
    @Builder
    public AddReviewReq(String content) {
        this.content = content;
    }
    @NotBlank(message = "한 글자 이상 리뷰를 작성해주세요.")
    private String content;
}