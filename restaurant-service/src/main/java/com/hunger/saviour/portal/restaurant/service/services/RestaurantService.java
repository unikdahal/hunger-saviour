package com.hunger.saviour.portal.restaurant.service.services;

import com.hunger.saviour.portal.restaurant.service.dtos.RestaurantDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestaurantService {
    public void createRestaurant(RestaurantDTO restaurantDTO);

}
