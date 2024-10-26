package com.polar_moviechart.userservice.domain.utils;

public class TestUtils {

    public static void sleep(Long sleepsSecond) {
        try {
            Thread.sleep(sleepsSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
