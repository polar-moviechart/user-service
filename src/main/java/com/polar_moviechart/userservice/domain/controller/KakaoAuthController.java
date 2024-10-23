package com.polar_moviechart.userservice.domain.controller;

import com.polar_moviechart.userservice.domain.service.kakao.KaKaoTokenResponse;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoTokenService;
import com.polar_moviechart.userservice.domain.service.kakao.KakaoUserInfoDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/kakao")
public class KakaoAuthController {
    private final KakaoTokenService kakaoTokenService;

    @GetMapping("/login/callback")
    public void getKakaoExternalId(@RequestParam(name = "code") String code,
                                   HttpServletResponse httpResponse) {
        KaKaoTokenResponse kaKaoTokenResponse = kakaoTokenService.getTokenAndRedirectUser(code);
        KakaoUserInfoDto kakaoUserInfoDto = kakaoTokenService.getUserId(kaKaoTokenResponse.getAccess_token());

        String redirectUrl = "http://localhost:3000/kakaoAuth?id=" + kakaoUserInfoDto.getId();
        try {
            httpResponse.sendRedirect(redirectUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
