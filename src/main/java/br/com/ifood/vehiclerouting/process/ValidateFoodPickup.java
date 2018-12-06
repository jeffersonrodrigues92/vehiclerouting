package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidateFoodPickup implements IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    @Autowired
    private OrderRepository orderRepository;

    public List process(Object...orders) throws IfoodProcessException {

        Iterable<Orders> ordersList = orderRepository.findAll();
        List<Orders> ordersReady = new ArrayList<>();

        ordersList.forEach(order ->{
            if(new Date().equals(order.getPickup()) || new Date().after(order.getPickup())){
                 ordersReady.add(order);
            }
        });

        if(ordersReady.isEmpty()){
            throw new IfoodProcessException("Ainda não tem nenhum pedido pronto, por favor volte mais tarde.");
        }

        return ordersReady;
    }

    public void setNextIfoodProcess(IfoodProcess ifoodProcess) {
        nextIfoodProcess = ifoodProcess;
    }
}
