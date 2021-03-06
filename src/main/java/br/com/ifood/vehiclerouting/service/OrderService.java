package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Client;
import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.repository.ClientRepository;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import br.com.ifood.vehiclerouting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<Void> create (Order order) {

        Client client = clientRepository.findById(order.getClient().getId()).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant().getId()).orElse(null);
        if (Objects.isNull(client) || Objects.isNull(restaurant))
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Order get(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findByOrderBewteenDeliveryTime(String startDevelivery, String endDelivery){
        List<Order> orders = orderRepository.findByOrderBewteenDeliveryTime(startDevelivery, endDelivery).orElse(null);;
        return orders;
    }
}
