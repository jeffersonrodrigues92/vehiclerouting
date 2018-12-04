package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}
