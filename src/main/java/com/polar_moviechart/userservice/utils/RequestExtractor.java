package com.polar_moviechart.userservice.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestExtractor {
    public static Long getUserId(HttpServletRequest servletRequest) {
        String userIdString = servletRequest.getHeader("X-User-Id");
        if (userIdString == null) {
            return null;
        } else {
            return Long.parseLong(userIdString);
        }
    }
}