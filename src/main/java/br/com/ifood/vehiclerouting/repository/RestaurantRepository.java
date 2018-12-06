package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
}
