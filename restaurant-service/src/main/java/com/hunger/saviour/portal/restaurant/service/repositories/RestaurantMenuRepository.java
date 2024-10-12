package com.hunger.saviour.portal.restaurant.service.repositories;

import com.hunger.saviour.portal.restaurant.service.entities.RestaurantMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenuEntity,Integer> {

}
