package com.hunger.saviour.portal.restaurant.service.dtos;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class RestaurantMenuDTO {


    private Integer restaurantMenuId;

    @NotBlank(message = "Restaurant menuItem name is mandatory")
    private String menuItem;

    @NotBlank(message = "Restaurant menuItem description is mandatory")
    private String description;

    @NotBlank(message = "Restaurant menuItem image url is mandatory")
    private String menuImageUrl;

    @NotBlank(message = "Restaurant menuType is mandatory")
    private String menuType;

    @NotBlank(message = "Restaurant menu item price is mandatory")
    private Double menuItemPrice;

    private RestaurantEntity restaurant;
}
