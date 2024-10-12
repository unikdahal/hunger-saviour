package com.hunger.saviour.portal.restaurant.service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant_menus")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_menu_id")
    private Integer restaurantMenuId;

    @Column(name="menu_item")
    private String menuItem;

    @Column(name = "description")
    private String description;

    @Column(name = "menu_image_url")
    private String menuImageUrl;

    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "menu_item_price")
    private Double menuItemPrice;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

}
