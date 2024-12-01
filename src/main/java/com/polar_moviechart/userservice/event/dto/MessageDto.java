package com.polar_moviechart.userservice.event.dto;

public interface MessageDto {
    String getType();
    Integer getCode();
    Object getValue();
}