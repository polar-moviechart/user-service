package com.polar_moviechart.userservice.domain.service.kakao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KakaoTokenService {
    private final RestTemplate restTemplate;

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;
    private final String tokenUrl = "https://kauth.kakao.com/oauth/token";

    public KaKaoTokenResponse getToken(String code) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = String.format("grant_type=authorization_code" +
                        "&client_id=%s" +
                        "&redirect_uri=%s" +
                        "&code=%s",
                clientId,
                redirectUri,
                code);

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<KaKaoTokenResponse> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, KaKaoTokenResponse.class);
        return response.getBody();
    }
}
