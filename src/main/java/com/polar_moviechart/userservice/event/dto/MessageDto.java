package com.polar_moviechart.userservice.event.dto;

public interface MessageDto {
    MessageType getType();
    Integer getCode();
    Object getValue();
}