package com.polar_moviechart.userservice.domain.controller.secureapi;

import com.polar_moviechart.userservice.domain.entity.Role;
import com.polar_moviechart.userservice.domain.service.jwt.JwtProvider;
import com.polar_moviechart.userservice.utils.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/secure/api/v1/users")
public class UserControllerSecure {

    private final JwtProvider jwtProvider;

    @PostMapping("/generateToken")
    public ResponseEntity<CustomResponse<AccessTokenDto>> generateToken(
            HttpServletRequest servletRequest) {
        String userId = servletRequest.getHeader("X-User-Id");
        String accessToken = jwtProvider.createAccessToken(Long.parseLong(userId), Role.USER);

        CustomResponse<AccessTokenDto> customResponse = new CustomResponse<>(new AccessTokenDto(accessToken));
        return ResponseEntity.ok(customResponse);
    }
}
