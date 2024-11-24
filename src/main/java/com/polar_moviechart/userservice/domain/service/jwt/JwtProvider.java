package com.polar_moviechart.userservice.domain.service.jwt;

import com.polar_moviechart.userservice.domain.entity.Role;
import com.polar_moviechart.userservice.exception.ErrorCode;
import com.polar_moviechart.userservice.exception.TokenCreationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtProvider {

    public static final String TOKEN_EXPIRED_MESSAGE = "토큰이 만료되었습니다.";
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
        return parseToken(refreshToken)
                .map(claims -> {
                    validateClaims(claims);
                    return createToken(
                            Long.parseLong(claims.getSubject()),
                            Role.valueOf(claims.get("role").toString()),
                            accessTokenValidityInMilliseconds);
                })
                .get();
    }

    public Optional<Claims> validateClaims(Claims claims) {
        boolean isExpired = isExpired(claims);
        if (isExpired) {
            throw new TokenCreationException(ErrorCode.TOKEN_EXPIRED);
        }
        return Optional.of(claims);
    }

    private boolean isExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration != null && expiration.before(new Date());
    }

    public Optional<Claims> parseToken(String token) {
        try {
            return Optional.of(Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody());
        } catch (ExpiredJwtException e) {
            throw new TokenCreationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new TokenCreationException(ErrorCode.TOKEN_CREATION_ERROR, e);
        }
    }

    public String createAccessToken(Long userId, Role role) {
        return createToken(userId, role, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken(Long userId, Role role) {
        return createToken(userId, role, refreshTokenValidityInMilliseconds);
    }

    private String createToken(Long userId, Role role, long validityInMilliseconds) {
        Claims claims = Optional.ofNullable(
                Jwts.claims().setSubject(userId.toString())
        ).orElseThrow(() -> new TokenCreationException(ErrorCode.TOKEN_CANNOT_BE_NULL));

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
