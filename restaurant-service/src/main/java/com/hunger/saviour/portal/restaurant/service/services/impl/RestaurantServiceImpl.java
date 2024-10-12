package com.hunger.saviour.portal.restaurant.service.services.impl;

import com.hunger.saviour.portal.restaurant.service.dtos.RestaurantDTO;
import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import com.hunger.saviour.portal.restaurant.service.repositories.RestaurantRepository;
import com.hunger.saviour.portal.restaurant.service.services.RestaurantService;
import com.hunger.saviour.portal.restaurant.service.utilities.RestaurantUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public void createRestaurant(RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = RestaurantUtility.convertDTOToEntity(restaurantDTO);
        this.restaurantRepository.save(restaurantEntity);
    }
}
