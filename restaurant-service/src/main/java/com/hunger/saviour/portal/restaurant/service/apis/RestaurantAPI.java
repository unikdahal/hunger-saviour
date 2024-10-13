package com.hunger.saviour.portal.restaurant.service.apis;

import com.hunger.saviour.portal.restaurant.service.dtos.RestaurantDTO;
import com.hunger.saviour.portal.restaurant.service.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurants")
@RequiredArgsConstructor
public class RestaurantAPI {

    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        this.restaurantService.createRestaurant(restaurantDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{offset}/{pagesize}")
    public ResponseEntity<?> getRestaurants(@PathVariable int offset, @PathVariable int pagesize) {
        this.restaurantService.getRestaurants(offset, pagesize);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
