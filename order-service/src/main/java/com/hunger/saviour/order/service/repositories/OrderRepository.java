package com.hunger.saviour.order.service.repositories;

import com.hunger.saviour.order.service.entities.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<OrderEntity, UUID> {
}
