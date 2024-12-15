package com.polar_moviechart.userservice.controller.internalapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserMoviesLikeReq {
    private Long userId;
    private List<Integer> movieCodes;
}
