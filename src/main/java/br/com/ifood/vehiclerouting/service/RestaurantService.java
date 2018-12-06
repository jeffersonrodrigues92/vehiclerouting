package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public Long update(Restaurant restaurant) {
        restaurant = restaurantRepository.save(restaurant);
        return restaurant.getId();
    }


    public Restaurant get(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }
}
