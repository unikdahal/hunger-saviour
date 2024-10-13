package com.hunger.saviour.portal.restaurant.service.utilities;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import com.hunger.saviour.portal.restaurant.service.entities.RestaurantMenuEntity;
import com.hungersaviour.portal.commons.dtos.RestaurantDTO;
import com.hungersaviour.portal.commons.dtos.RestaurantMenuDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtility {

    public static RestaurantEntity convertDTOToEntity(RestaurantDTO restaurantDTO) {
        List<RestaurantMenuEntity> restaurantMenuEntities = restaurantDTO.getMenuItems().stream()
                .map(RestaurantUtility::convertDTOToEntity)
                .collect(Collectors.toList());

        return RestaurantEntity.builder()
                .restaurantName(restaurantDTO.getRestaurantName())
                .rating(restaurantDTO.getRating())
                .location(restaurantDTO.getLocation())
                .imageUrl(restaurantDTO.getImageUrl())
                .menuTypes(restaurantDTO.getMenuTypes())
                .menuItems(restaurantMenuEntities)
                .build();
    }

    public static RestaurantMenuEntity convertDTOToEntity(RestaurantMenuDTO restaurantMenuDTO) {
        return RestaurantMenuEntity.builder()
                .menuImageUrl(restaurantMenuDTO.getMenuImageUrl())
                .description(restaurantMenuDTO.getDescription())
                .menuType(restaurantMenuDTO.getMenuType())
                .menuItemPrice(restaurantMenuDTO.getMenuItemPrice())
                .build();
    }

    public static RestaurantDTO convertEntityToDTO(RestaurantEntity restaurantEntity) {
        List<RestaurantMenuDTO> restaurantMenuDTOList = restaurantEntity.getMenuItems().stream()
                .map(RestaurantUtility::convertEntityToDTO)
                .collect(Collectors.toList());

        return RestaurantDTO.builder()
                .restaurantName(restaurantEntity.getRestaurantName())
                .rating(restaurantEntity.getRating())
                .location(restaurantEntity.getLocation())
                .imageUrl(restaurantEntity.getImageUrl())
                .menuTypes(restaurantEntity.getMenuTypes())
                .menuItems(restaurantMenuDTOList)
                .build();
    }

    public static RestaurantMenuDTO convertEntityToDTO(RestaurantMenuEntity restaurantMenuEntity) {
        return RestaurantMenuDTO.builder()
                .menuImageUrl(restaurantMenuEntity.getMenuImageUrl())
                .description(restaurantMenuEntity.getDescription())
                .menuType(restaurantMenuEntity.getMenuType())
                .menuItemPrice(restaurantMenuEntity.getMenuItemPrice())
                .build();
    }
}
