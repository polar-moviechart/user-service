package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.dtos.UserActivityInfo;
import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.repository.UserRepository;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.UserBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    public Optional<User> getUserOptional(AuthType authType, Long kakaoUserId) {
        return userRepository.findByAuthTypeAndExternalId(authType, kakaoUserId);
    }

    public User getKakaoUser(AuthType authType, Long kakaoUserId) {
        return userRepository.findByAuthTypeAndExternalId(authType, kakaoUserId)
                .orElseThrow(() -> new UserBusinessException(ErrorCode.USER_NOT_EXISTS));
    }

    public boolean isExists(Long userId) {
        return userRepository.existsById(userId);
    }

    public void validateExist(Long userId) {
        if (!isExists(userId)) {
            throw new UserBusinessException(ErrorCode.USER_NOT_EXISTS);
        }
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserBusinessException(ErrorCode.USER_NOT_EXISTS));
    }
}
