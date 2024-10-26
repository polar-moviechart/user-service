package com.polar_moviechart.userservice.domain.controller;

import com.polar_moviechart.userservice.domain.entity.AuthType;
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
        Long userId = kakaoUserProcessor.processKakaoLogin(AuthType.KAKAO, req.getId());
        TokenResponse tokenResponse = jwtProvider.generateTokens(userId, Role.USER);
        CustomResponse<TokenResponse> customResponse = new CustomResponse<>(tokenResponse);

        return ResponseEntity.ok(customResponse);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<CustomResponse<AccessTokenDto>> generateToken(@RequestBody RefreshTokenDto req) {
        String accessToken = jwtProvider.createAccessToken(req.getRefreshToken());
        CustomResponse<AccessTokenDto> customResponse = new CustomResponse<>(new AccessTokenDto(accessToken));
        return ResponseEntity.ok(customResponse);
    }
}
