package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.UserTestConfig;
import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserQueryServiceTest extends UserTestConfig {

    @Autowired
    private UserQueryService userQueryService;

    private User user;

    @BeforeEach
    void setUp() {
        initUsers(1);
        this.user = getUsers().get(0);
    }

    @Test
    void getUserExists() {
        Optional<User> userOptional = userQueryService.getUserOptional(AuthType.KAKAO, 1L);
        assertTrue(userOptional.isPresent());
    }

    @Test
    void getUserDoesntExists() {
        Optional<User> userOptional = userQueryService.getUserOptional(AuthType.KAKAO, 10L);
        assertTrue(userOptional.isEmpty());
    }

    @Test
    void isExistsTest() {
        assertTrue(userQueryService.isExists(user.getId()));
        assertFalse(userQueryService.isExists(10L));
    }
}