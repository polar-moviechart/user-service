package com.polar_moviechart.userservice.domain.controller.secureapi.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RefreshTokenDto {
    private final String refreshToken;
}
