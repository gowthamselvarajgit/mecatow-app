package com.mecatow.user_service.exception;

import com.mecatow.user_service.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        ErrorResponseDto errorResponse = buildErrorResponse(exception.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errorMap.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentialsException(InvalidCredentialsException exception){
        ErrorResponseDto errorResponseDto = buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseDto);
    }

    private ErrorResponseDto buildErrorResponse(String message, HttpStatus status){

        return ErrorResponseDto.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .build();

    }
}
