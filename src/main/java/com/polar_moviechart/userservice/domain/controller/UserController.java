package com.polar_moviechart.userservice.domain.controller;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.Role;
import com.polar_moviechart.userservice.domain.service.jwt.JwtProvider;
import com.polar_moviechart.userservice.domain.service.jwt.TokenResponse;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoTokenService;
import com.polar_moviechart.userservice.domain.service.kakao.KaKaoTokenResponse;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoUserInfoResponse;
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
    private final KakaoTokenService kakaoTokenService;
    private final KakaoUserProcessor kakaoUserProcessor;
    private final JwtProvider jwtProvider;

    @PostMapping("/login/kakao/callback")
    public ResponseEntity<CustomResponse<TokenResponse>> kakaoLogin(@RequestBody KakaoCodeDto req) {
        KaKaoTokenResponse kaKaoTokenResponse = kakaoTokenService.getToken(req.getCode());
        KakaoUserInfoResponse kakaoInfoResponse = kakaoTokenService.getUserId(kaKaoTokenResponse.getAccess_token());
        Long userId = kakaoUserProcessor.processKakaoLogin(AuthType.KAKAO, kakaoInfoResponse.getId());
        TokenResponse tokenResponse = jwtProvider.generateTokens(userId, Role.USER);

        return ResponseEntity.ok(new CustomResponse<>(tokenResponse));
    }
}
