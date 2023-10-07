package com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.adapter;

import com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.repository.RestaurantRepositoryJpa;
import com.food.ordering.system.order.service.dataaccess.restaurant.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantRepositoryJpa restaurantRepositoryJpa;
    private final RestaurantDataAccessMapper restaurantDataAccessMapper;

    public RestaurantRepositoryImpl(RestaurantRepositoryJpa restaurantRepositoryJpa, RestaurantDataAccessMapper restaurantDataAccessMapper) {
        this.restaurantRepositoryJpa = restaurantRepositoryJpa;
        this.restaurantDataAccessMapper = restaurantDataAccessMapper;
    }

    @Override
    public Optional<Restaurant> findRestaurantInformation(Restaurant restaurant) {
        List<UUID> restaurantProducts = restaurantDataAccessMapper
                .restaurantToRestaurantProducts(restaurant);

        Optional<List<RestaurantEntity>> restaurantEntities = restaurantRepositoryJpa
                .findByRestaurantIdAndProductIdIn(restaurant.getId().getValue(), restaurantProducts);

        return restaurantEntities.map(restaurantDataAccessMapper::restaurantEntityToRestaurant);
    }
}
