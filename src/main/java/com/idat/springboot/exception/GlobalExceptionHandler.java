package com.idat.springboot.exception;

import java.util.Map;
import java.util.stream.Collectors;

import com.idat.springboot.common.ResponseCode;
import com.idat.springboot.common.ApiResponseUtil;
import com.idat.springboot.common.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existing, replacement) -> existing
                ));
        logger.error("Validation failed: {}", errors, ex);        
        return ResponseEntity.badRequest().body(ApiResponseUtil.error(ResponseCode.VALIDATION_ERROR, "Validation failed", errors));
    }

    @ExceptionHandler(AlumnoNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlumnoNotFoundException(AlumnoNotFoundException ex) {
        logger.error("Alumno not found: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseUtil.error(ResponseCode.NOT_FOUND, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseUtil.error(ResponseCode.INTERNAL_ERROR, "An unexpected error occurred", null));
    }

}
