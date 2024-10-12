package com.hunger.saviour.portal.restaurant.service.dtos;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantMenuEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class RestaurantDTO {
    private Integer restaurantId;

    @NotBlank(message = "Restaurant Name is Mandatory")
    private String restaurantName;

    private String rating;

    @NotBlank(message = "Restaurant Image is Mandatory")
    private String imageUrl;

    @NotBlank(message = "Restaurant Location is Mandatory")
    private String location;

    @NotNull(message = "Restaurant Menu Types are mandatory")
    private List<String> menuTypes;

    @NotNull(message = "There must be at least one menu item")
    private List<RestaurantMenuEntity> menuItems;
}