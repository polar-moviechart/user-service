package com.polar_moviechart.userservice.domain.controller.secureapi;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenDto {
    private final String accessToken;
}
