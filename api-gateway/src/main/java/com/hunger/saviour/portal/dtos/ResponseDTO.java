package com.hunger.saviour.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ResponseDTO is a generic wrapper for API responses.
 * It ensures consistent structure for both successful and error responses in the API layer.
 *
 * @param <T> The generic type for the "data" field.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO<T> {

    private Boolean success;            // Indicates if the response was successful
    private String message;            // A user-friendly message
    private T data;                    // Generic response payload (null in case of error)
    private String errorCode;          // Standardized error code for easy debugging
    private LocalDateTime localTimestamp; // Timestamp for when the response was created
}