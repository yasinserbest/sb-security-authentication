package com.maystorre.sb_security_authentication.exceptions;

import com.maystorre.sb_security_authentication.model.dto.user.exception.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepitonHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ExceptionResponseDto> myAPIException  (APIException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                e.getMessage(),
                e.getHttpStatus().value(),
                e.getHttpStatus().getReasonPhrase()
                );
        return new ResponseEntity<>(exceptionResponseDto, e.getHttpStatus());
    }
}