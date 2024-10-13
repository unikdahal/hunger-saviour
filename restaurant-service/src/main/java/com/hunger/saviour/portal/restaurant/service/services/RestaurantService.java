package com.hunger.saviour.portal.restaurant.service.services;

import com.hunger.saviour.portal.restaurant.service.dtos.RestaurantDTO;
import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import org.springframework.data.domain.Page;

public interface RestaurantService {
    public void createRestaurant(RestaurantDTO restaurantDTO);
    public Page<RestaurantEntity> getRestaurants(int offset, int pagesize);
}
