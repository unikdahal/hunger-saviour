package com.hunger.saviour.portal.restaurant.service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="restaurants")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return getRestaurantId() != null && Objects.equals(getRestaurantId(), that.getRestaurantId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
