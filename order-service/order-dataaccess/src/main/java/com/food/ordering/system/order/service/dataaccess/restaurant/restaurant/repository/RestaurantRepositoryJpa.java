package com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.repository;

import com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.entity.RestaurantEntityId;
import com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepositoryJpa extends JpaRepository<RestaurantEntity, RestaurantEntityId> {
    Optional<List<RestaurantEntity>> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
