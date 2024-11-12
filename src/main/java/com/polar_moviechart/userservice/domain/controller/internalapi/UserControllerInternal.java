package com.polar_moviechart.userservice.domain.controller.internalapi;

import com.polar_moviechart.userservice.domain.service.UserQueryService;
import com.polar_moviechart.userservice.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/api/v1/users")
public class UserControllerInternal {

    private final UserQueryService userQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<CustomResponse<Boolean>> userExists(@PathVariable("userId") Long userId) {
        boolean isExists = userQueryService.isExists(userId);
        return ResponseEntity.ok(new CustomResponse(isExists));
    }
}
