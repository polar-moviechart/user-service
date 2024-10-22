package com.polar_moviechart.userservice.domain.service.jwt;

import com.polar_moviechart.userservice.domain.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

class JwtProviderTest {
    private JwtProvider jwtProvider;
    private final String secretKey = "polarMoviechartJwtSecretPolarMoviechartJwtSecret";

    @BeforeEach
    void setUp() {
        long accessTokenValidityInMilliseconds = 2 * 1000L; // 1초
        long refreshTokenValidityInMilliseconds = 86400 * 1000L; // 24시간
        jwtProvider = new JwtProvider(secretKey, accessTokenValidityInMilliseconds, refreshTokenValidityInMilliseconds);
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
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .getBody();
        long extractedUserId = Long.parseLong(claims.getSubject());
        String extractedRoleString = (String) claims.get("role");
        Role extractedRole = Role.valueOf(extractedRoleString);

        Assertions.assertThat(extractedUserId).isEqualTo(userId);
        Assertions.assertThat(extractedRole).isEqualTo(role);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertThatThrownBy(() -> {
                    Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
                })
                .isInstanceOf(ExpiredJwtException.class)
                .hasMessageContaining("JWT expired");
    }
}