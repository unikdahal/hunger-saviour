package com.hunger.saviour.order.service.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    private UUID orderId;

    private UUID userId;

    private LocalDateTime orderDate;

    private LocalDateTime modifiedDate;

    private OrderStatus orderStatus;

    private Double totalPrice;

    private Integer restaurantId;

    private String restaurant_json;
}
