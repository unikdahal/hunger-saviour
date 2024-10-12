package com.hunger.saviour.portal.restaurant.service.utilities;

import com.hunger.saviour.portal.restaurant.service.dtos.RestaurantDTO;
import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;

public class RestaurantUtility {

    public static RestaurantEntity convertDTOToEntity(RestaurantDTO restaurantDTO) {
        return RestaurantEntity.builder()
                .restaurantName(restaurantDTO.getRestaurantName())
                .rating(restaurantDTO.getRating())
                .location(restaurantDTO.getLocation())
                .imageUrl(restaurantDTO.getImageUrl())
                .menuTypes(restaurantDTO.getMenuTypes())
                .menuItems(restaurantDTO.getMenuItems())
                .build();
    }

    public static RestaurantDTO convertEntityToDTO(RestaurantEntity restaurantEntity) {
        return RestaurantDTO.builder()
                .restaurantName(restaurantEntity.getRestaurantName())
                .rating(restaurantEntity.getRating())
                .location(restaurantEntity.getLocation())
                .imageUrl(restaurantEntity.getImageUrl())
                .menuTypes(restaurantEntity.getMenuTypes())
                .menuItems(restaurantEntity.getMenuItems())
                .build();
    }
}
