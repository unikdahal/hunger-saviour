package com.hunger.saviour.portal.restaurant.service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "restaurant_menus")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RestaurantMenuEntity that = (RestaurantMenuEntity) o;
        return getRestaurantMenuId() != null && Objects.equals(getRestaurantMenuId(), that.getRestaurantMenuId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
