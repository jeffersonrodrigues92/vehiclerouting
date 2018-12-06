package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
}
