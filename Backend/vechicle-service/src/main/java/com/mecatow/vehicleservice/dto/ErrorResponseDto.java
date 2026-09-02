package com.mecatow.vehicleservice.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ErrorResponseDto {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private List<String> errors;
    private String path;
}
