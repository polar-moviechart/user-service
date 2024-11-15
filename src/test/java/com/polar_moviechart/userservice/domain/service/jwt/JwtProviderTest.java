package com.polar_moviechart.userservice.domain.service.jwt;

import com.polar_moviechart.userservice.domain.entity.Role;
import com.polar_moviechart.userservice.domain.utils.TestUtils;
import com.polar_moviechart.userservice.exception.TokenCreationException;
import io.jsonwebtoken.Claims;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class JwtProviderTest {
    private JwtProvider jwtProvider;
    private final String secretKey = "polarMoviechartJwtSecretPolarMoviechartJwtSecretTest";
    private final long atkExpiredTime = 1000L;
    private final long rtkExpiredTime = 100 * 1000L;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider(secretKey, atkExpiredTime, rtkExpiredTime);
    }

    @DisplayName("엑세스 토큰 발급 및 검증")
    @Test
    void testJwtTokenCreationAndValidation() {
        // given
        Long userId = 1L;
        Role role = Role.USER;

        // when
        String accessToken = jwtProvider.createAccessToken(userId, role);

        // then
        Optional<Claims> claimsOptional = jwtProvider.parseToken(accessToken);
        Claims claims = claimsOptional.get();

        long extractedUserId = Long.parseLong(claims.getSubject());
        String extractedRoleString = (String) claims.get("role");
        Role extractedRole = Role.valueOf(extractedRoleString);

        Assertions.assertThat(extractedUserId).isEqualTo(userId);
        Assertions.assertThat(extractedRole).isEqualTo(role);

        TestUtils.sleep(atkExpiredTime);

        Assertions.assertThatThrownBy(() -> {
                    jwtProvider.validateClaims(claims);
                })
                .isInstanceOf(TokenCreationException.class)
                .hasMessageContaining(jwtProvider.TOKEN_EXPIRED_MESSAGE);
    }

    @DisplayName("엑세스 토큰이 만료된 경우 리프레쉬 토큰을 통해 재발급할 수 있다.")
    @Test
    void test() {
        // given
        TokenResponse tokenResponse = jwtProvider.generateTokens(1L, Role.USER);

        // when
        String accessToken = tokenResponse.getAccessToken();
        Claims claims = jwtProvider.parseToken(accessToken).get();

        TestUtils.sleep(atkExpiredTime);

        Assertions.assertThatThrownBy(() -> {
            jwtProvider.validateClaims(claims);
        })
        .isInstanceOf(TokenCreationException.class)
        .hasMessageContaining(jwtProvider.TOKEN_EXPIRED_MESSAGE);

        String newAccessToken = jwtProvider.createAccessToken(tokenResponse.getRefreshToken());
        Claims newClaims = jwtProvider.parseToken(newAccessToken).get();
        // then
        Assertions.assertThat(jwtProvider.validateClaims(newClaims))
                .isPresent();
    }
}