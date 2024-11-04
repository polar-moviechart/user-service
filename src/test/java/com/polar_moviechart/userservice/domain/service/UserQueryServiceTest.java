package com.polar_moviechart.userservice.domain.service;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserQueryServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    @Test
    void getUserExists() {
        Optional<User> userOptional = userQueryService.getUser(AuthType.KAKAO, 1L);
        assertTrue(userOptional.isPresent());
    }

    @Test
    void getUserDoesntExists() {
        Optional<User> userOptional = userQueryService.getUser(AuthType.KAKAO, 10L);
        assertTrue(userOptional.isEmpty());
    }

    @Test
    void isExistsTest() {
        assertTrue(userQueryService.isExists(1L));
        assertFalse(userQueryService.isExists(10L));
    }
}