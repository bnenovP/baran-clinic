package com.example.baranclinic.common.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponseDTO {

    private final HttpStatus status;

    private final String message;
}
