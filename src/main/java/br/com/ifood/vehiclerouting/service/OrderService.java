package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Client;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.repository.ClientRepository;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import br.com.ifood.vehiclerouting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<Void> create (Orders order) {

        order.setPickup(new Date());
        order.setDelivery(Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()));

        Client client = clientRepository.findById(order.getClient()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant()).orElse(null);
        if (Objects.isNull(client) || Objects.isNull(restaurant))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Orders get(Long id){
        return orderRepository.findById(id).orElse(null);
    }
}
