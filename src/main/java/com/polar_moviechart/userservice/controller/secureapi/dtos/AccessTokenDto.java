package com.polar_moviechart.userservice.controller.secureapi.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenDto {
    private final String accessToken;
}
