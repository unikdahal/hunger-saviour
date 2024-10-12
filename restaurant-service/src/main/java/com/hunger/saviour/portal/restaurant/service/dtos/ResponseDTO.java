package com.hunger.saviour.portal.restaurant.service.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private List<String> errorMessages; //for jakarta validation annotations

    private String exceptionMessage; //need to send the exception that occurred in our app

    private String statusCodeDescription;

    private LocalDateTime timestamp;

    private RestaurantDTO restaurantDetails;


}
