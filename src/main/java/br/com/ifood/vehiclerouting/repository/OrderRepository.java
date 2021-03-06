package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE delivery BETWEEN ?1 AND ?2")
    Optional<List<Order>>findByOrderBewteenDeliveryTime(String startDevelirey, String endDelivery);
}
