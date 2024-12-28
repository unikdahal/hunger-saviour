package com.hunger.saviour.portal.handlers;

import com.hunger.saviour.portal.dtos.ResponseDTO;
import com.hunger.saviour.portal.exceptions.UnauthorizedException;
import com.hunger.saviour.portal.util.ErrorResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the API Gateway.
 * Manages structured error responses and correlation ID logging.
 */
@RestControllerAdvice
@Slf4j
public class ApiGatewayHandler {

    /**
     * Handles {@link UnauthorizedException} and returns a structured response.
     *
     * @param ex The thrown UnauthorizedException.
     * @return A {@link ResponseEntity} containing a detailed error message.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ResponseDTO<Void>> handleUnauthorizedException(UnauthorizedException ex) {
        String requestId = MDC.get("requestId");
        log.warn("Unauthorized access detected - Request ID: {}, Error: {}", requestId, ex.getMessage());

        return new ResponseEntity<>(ErrorResponseUtil.buildResponse(false, ex.getMessage(), null,
                "ERR_UNAUTHORIZED", requestId), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles generic exceptions and formats them into structured responses.
     *
     * @param ex The thrown generic exception.
     * @return A {@link ResponseEntity} containing the error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handleGenericException(Exception ex) {
        String requestId = MDC.get("requestId");
        log.error("An unexpected error occurred - Request ID: {}, Error: {}", requestId, ex.getMessage());

        return new ResponseEntity<>(ErrorResponseUtil.buildResponse(false, "An unexpected error occurred.",
                null, "ERR_INTERNAL_SERVER_ERROR", requestId), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}