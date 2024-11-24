package com.polar_moviechart.userservice.domain.repository;

import com.polar_moviechart.userservice.domain.UserTestConfig;
import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends UserTestConfig {

    @DisplayName("유저 저장 테스트")
    @Test
    void save() {
        // given
        User user = User.builder()
                .authType(AuthType.KAKAO)
                .nickname("testUser")
                .externalId(1L)
                .build();
        // when
        User savedUser = userRepository.save(user);
        // then
        assertThat(userRepository.findById(savedUser.getId())).isPresent();
    }
}