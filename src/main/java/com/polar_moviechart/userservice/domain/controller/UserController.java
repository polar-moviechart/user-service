package com.polar_moviechart.userservice.domain.controller;

import com.polar_moviechart.userservice.domain.service.kakao.KakaoTokenService;
import com.polar_moviechart.userservice.domain.service.kakao.KaKaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final KakaoTokenService kakaoTokenService;

    @PostMapping("/login/kakao/callback")
    public void kakaoLogin(@RequestBody KakaoCodeDto req) {
        KaKaoTokenResponse kaKaoTokenResponse = kakaoTokenService.getToken(req.getCode());
    }
}
