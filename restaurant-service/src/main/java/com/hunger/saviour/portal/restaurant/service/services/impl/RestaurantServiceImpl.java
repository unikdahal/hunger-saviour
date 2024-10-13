package com.hunger.saviour.portal.restaurant.service.services.impl;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import com.hunger.saviour.portal.restaurant.service.repositories.RestaurantRepository;
import com.hunger.saviour.portal.restaurant.service.services.RestaurantService;
import com.hunger.saviour.portal.restaurant.service.utilities.RestaurantUtility;
import com.hungersaviour.portal.commons.dtos.RestaurantDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public void createRestaurant(@Valid RestaurantDTO restaurantDTO) {
        RestaurantEntity restaurantEntity = RestaurantUtility.convertDTOToEntity(restaurantDTO);
        this.restaurantRepository.save(restaurantEntity);
    }

    @Override
    public Page<RestaurantEntity> getRestaurants(int offset, int pagesize) {
        return this.restaurantRepository.findAll(PageRequest.of(offset, pagesize));
    }
}
