package com.polar_moviechart.userservice.domain.service.event.dto;

public interface MessageDto {
    String getType();
    Integer getCode();
    Object getValue();
}