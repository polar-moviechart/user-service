package com.polar_moviechart.userservice.domain.controller;

import com.polar_moviechart.userservice.domain.entity.Role;
import com.polar_moviechart.userservice.domain.service.jwt.JwtProvider;
import com.polar_moviechart.userservice.domain.service.jwt.TokenResponse;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoUserInfoDto;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoUserProcessor;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final KakaoUserProcessor kakaoUserProcessor;
    private final JwtProvider jwtProvider;

    @PostMapping("/login/kakao")
    public ResponseEntity<CustomResponse<TokenResponse>> kakaoLogin(@RequestBody KakaoUserInfoDto req) {
        Long userId = kakaoUserProcessor.processKakaoLogin(req.getId());
        TokenResponse tokenResponse = jwtProvider.generateTokens(userId, Role.USER);
        CustomResponse<TokenResponse> customResponse = new CustomResponse<>(tokenResponse);

        return ResponseEntity.ok(customResponse);
    }
}
