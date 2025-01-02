package com.coolismo.coins_service.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record HttpErrorInfo(
    String message, String path, LocalDateTime localDateTime, HttpStatus httpStatus) {}
