package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/save")
    public ResponseEntity<Void> create(@RequestBody Restaurant restaurant){
        restaurantService.save(restaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/create")
    public ResponseEntity<Long> update(@RequestBody Restaurant restaurant){
        Long id = restaurantService.update(restaurant);
        if(Objects.nonNull(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant>getResturant(@PathVariable(value = "id") Long id){
        Restaurant restaurant = restaurantService.get(id);
        if(Objects.nonNull(restaurant)){
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }
        return new ResponseEntity<>(restaurant, HttpStatus.NOT_FOUND);
    }
}
