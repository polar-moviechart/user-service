package com.polar_moviechart.userservice;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import com.polar_moviechart.userservice.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class BaseTestConfig {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUpData() {
        userRepository.save(
                User.builder()
                        .nickname("testUser")
                        .externalId(1L)
                        .authType(AuthType.KAKAO)
                        .build());
    }
}
