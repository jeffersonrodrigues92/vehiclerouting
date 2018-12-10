package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidateFoodPickupProcess extends IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> process(){
        Iterable<Order> ordersList = orderRepository.findAll();
        List<Order> ordersReady = new ArrayList<>();

        ordersList.forEach(order ->{
            if(new Date().equals(order.getPickup()) || new Date().after(order.getPickup())){
                ordersReady.add(order);
            }
        });

        return ordersReady;
    }


    public void setNextIfoodProcess(IfoodProcess ifoodProcess) {
        nextIfoodProcess = ifoodProcess;
    }
}
