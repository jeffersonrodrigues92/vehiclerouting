package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid Orders order){
        return orderService.create(order);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orders>getResturant(@PathVariable(value = "id") Long id){
        Orders order = orderService.get(id);
        if(Objects.nonNull(order)){
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(order, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Orders>>getOrdersByDeliveryTime(@RequestParam(value = "startDelivery", required = true) String startDelivery, @RequestParam(value = "endDelivery", required = true) String endDelivery){
        List<Orders>orders = orderService.findByOrderBewteenDeliveryTime(startDelivery, endDelivery);
        if(Objects.nonNull(orders)){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(orders, HttpStatus.NOT_FOUND);
    }
}

