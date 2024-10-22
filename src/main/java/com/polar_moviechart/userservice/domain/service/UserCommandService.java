package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;



    @Transactional
    public User createKakaoUser(Long kakaoId) {
        String nickname = "유저_" + UUID.randomUUID().toString().substring(0, 7);

        User createdUser = User.builder()
                .nickname(nickname)
                .externalId(kakaoId)
                .authType(AuthType.KAKAO)
                .build();
        return userRepository.save(createdUser);
    }
}
