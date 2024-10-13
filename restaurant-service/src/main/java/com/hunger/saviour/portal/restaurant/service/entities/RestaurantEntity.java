package com.hunger.saviour.portal.restaurant.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    @SequenceGenerator(name = "res_seq",sequenceName= "restaurant_sequence", initialValue = 20100, allocationSize = 1)
    @GeneratedValue(generator = "res_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "rating")
    private String rating;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "location")
    private String location;

    @ElementCollection
    private List<String> menuTypes;

    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<RestaurantMenuEntity> menuItems;

}
