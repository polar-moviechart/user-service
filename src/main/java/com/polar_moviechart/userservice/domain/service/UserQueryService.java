package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    public Optional<User> getUser(AuthType authType, Long kakaoUserId) {
        return userRepository.findByAuthTypeAndExternalId(authType, kakaoUserId);
    }
}
