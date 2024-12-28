package com.hunger.saviour.portal.util;

import com.hunger.saviour.portal.dtos.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Utility class for handling and formatting error responses across the API Gateway.
 * Provides structured error responses for consistency.
 */
@Slf4j
public class ErrorResponseUtil {


    /**
     * Generates an error response and terminates the reactive pipeline.
     *
     * @param exchange {@link ServerWebExchange} for HTTP context.
     * @param message  Error message to be logged and returned.
     * @param status   HTTP status for the error response.
     * @param requestId Identifier for tracking the request.
     * @return A {@link Mono<Void>} indicating response completion.
     */
    public static Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status, String requestId) {
        log.warn("Error occurred - ID: {}, Message: {}", requestId, message);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().add("X-Request-ID", requestId);
        return response.setComplete();
    }

    /**
     * Handles unexpected exceptions and returns an internal server error response.
     *
     * @param exchange {@link ServerWebExchange} for HTTP context.
     * @param error    Caught exception or error throwable.
     * @param requestId Identifier for tracking the request.
     * @return A {@link Mono<Void>} indicating response completion.
     */
    public static Mono<Void> handleError(ServerWebExchange exchange, Throwable error, String requestId) {
        log.error("Request failed - ID: {}, Error: {}", requestId, error.getMessage(), error);
        return onError(exchange, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, requestId);
    }

    /**
     * Builds a generic response DTO for error handling.
     *
     * @param <T>       Response data type.
     * @param success   Flag indicating operation success.
     * @param message   Error or success message.
     * @param data      Optional response data.
     * @param errorCode Standardized error code.
     * @param requestId Request correlation ID.
     * @return A {@link ResponseDTO} encapsulating the error details.
     */
    public static <T> ResponseDTO<T> buildResponse(boolean success, String message, T data, String errorCode, String requestId) {
        return ResponseDTO.<T>builder()
                .success(success)
                .message(message)
                .data(data)
                .errorCode(errorCode)
                .localTimestamp(LocalDateTime.now())
                .build();
    }
}