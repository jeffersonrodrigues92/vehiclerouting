package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

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
}

