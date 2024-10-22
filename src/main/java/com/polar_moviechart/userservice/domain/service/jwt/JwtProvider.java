package com.polar_moviechart.userservice.domain.service.jwt;

import com.polar_moviechart.userservice.domain.entity.AuthType;
import com.polar_moviechart.userservice.domain.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    private final String secretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtProvider(
            @Value("${jwt.secetKey}") String secretKey,
            @Value("${jwt.accessTokenValidityInMilliseconds}") long accessTokenValidityInMilliseconds,
            @Value("${jwt.refreshTokenValidityInMilliseconds}") long refreshTokenValidityInMilliseconds) {
        this.secretKey = secretKey;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    public TokenResponse generateTokens(Long userId, Role role) {
        return new TokenResponse(
                createAccessToken(userId, role),
                createRefreshToken(userId, role)
        );
    }

    public String createAccessToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(refreshToken)
                .getBody();

        Long userId = Long.parseLong(claims.getSubject());
        Role role = Role.valueOf(claims.get("role").toString());

        return createToken(userId, role, accessTokenValidityInMilliseconds);
    }
    public String createAccessToken(Long userId, Role role) {
        return createToken(userId, role, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken(Long userId, Role role) {
        return createToken(userId, role, refreshTokenValidityInMilliseconds);
    }

    private String createToken(Long userId, Role role, long validityInMilliseconds) {
        Claims claims = Jwts.claims().setSubject(userId.toString());
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
