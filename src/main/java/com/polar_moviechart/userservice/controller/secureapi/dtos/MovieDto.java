package com.polar_moviechart.userservice.controller.secureapi.dtos;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MovieDto {
    private Long id;

    private int code;

    private String title;

    private String details;

    private LocalDate releaseDate;

    private Integer productionYear;

    private String synopsys;

    private Integer likeCnt;

    private Double rating;

    private Integer ratingCnt;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
