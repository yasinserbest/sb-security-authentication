package com.maystorre.sb_security_authentication.model.dto.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ExceptionResponseDto {
    private String message;
    private int status;
    private String error;
}
