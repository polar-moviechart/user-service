package com.polar_moviechart.userservice.event.dto;

public interface MessageDto {
    UserActivityType getType();
    Integer getCode();
    Object getValue();
}