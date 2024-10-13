package com.hunger.saviour.portal.restaurant.service.services;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import com.hungersaviour.portal.commons.dtos.RestaurantDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    public void createRestaurant(@Valid RestaurantDTO restaurantDTO);
    public Page<RestaurantEntity> getRestaurants(int offset, int pagesize);
}
