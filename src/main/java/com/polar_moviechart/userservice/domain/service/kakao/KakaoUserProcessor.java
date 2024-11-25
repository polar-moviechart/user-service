package com.polar_moviechart.userservice.domain.service.kakao;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.service.UserCommandService;
import com.polar_moviechart.userservice.domain.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KakaoUserProcessor {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;


    @Transactional
    public Long processKakaoLogin(Long kakaoUserId) {
        return userQueryService.getUserOptional(AuthType.KAKAO, kakaoUserId)
                .map(User::getId)
                .orElseGet(() -> {
                    User newUser = userCommandService.createKakaoUser(kakaoUserId);
                    return newUser.getId();
                });
    }
}
