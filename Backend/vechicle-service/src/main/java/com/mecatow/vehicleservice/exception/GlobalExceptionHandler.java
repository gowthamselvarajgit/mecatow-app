package com.mecatow.vehicleservice.exception;

import com.mecatow.vehicleservice.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleVehicleAlreadyExistsException(VehicleAlreadyExistsException vehicleAlreadyExistsException, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(buildErrorResponse(
                        HttpStatus.CONFLICT,
                        vehicleAlreadyExistsException.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(InvalidManufacturingYearException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidManufacturingYearException(InvalidManufacturingYearException invalidManufacturingYearException, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        invalidManufacturingYearException.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .distinct()
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(buildErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        "Validation Failed",
                        errors,
                        request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleVehicleNotFoundException(VehicleNotFoundException exception, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        buildErrorResponse(
                                HttpStatus.NOT_FOUND,
                                exception.getMessage(),
                                request.getRequestURI()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        buildErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Something went wrong",
                                request.getRequestURI()
                        )
                );
    }

    private ErrorResponseDto buildErrorResponse(HttpStatus status, String message, String path){
        return ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(message)
                .path(path)
                .build();
    }

    private ErrorResponseDto buildErrorResponse(
            HttpStatus status,
            String message,
            List<String> errors,
            String path
    ) {
        return ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .status(status.value())
                .error(status.name())
                .message(message)
                .errors(errors)
                .path(path)
                .build();
    }
}
