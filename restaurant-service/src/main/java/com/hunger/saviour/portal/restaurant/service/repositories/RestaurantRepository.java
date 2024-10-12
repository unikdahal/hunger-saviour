package com.hunger.saviour.portal.restaurant.service.repositories;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Integer> {
}
