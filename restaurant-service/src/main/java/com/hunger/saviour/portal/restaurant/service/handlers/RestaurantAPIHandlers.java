package com.hunger.saviour.portal.restaurant.service.handlers;

import com.hunger.saviour.portal.restaurant.service.dtos.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class RestaurantAPIHandlers {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.info("Entered into RestaurantAPIHandlers class with exception MethodArgumentNotValidException");
        return new ResponseEntity<>(ResponseDTO.builder()
                .errorMessages(
                        exception.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(ObjectError::getDefaultMessage)
                                .toList()
                )
                .statusCodeDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
