package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.bean.RouteBean;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
import br.com.ifood.vehiclerouting.process.AddOrdersDriverProcess;
import br.com.ifood.vehiclerouting.process.AddOrdersSameRestaurant;
import br.com.ifood.vehiclerouting.process.CalculateRouteDeliveryProcess;
import br.com.ifood.vehiclerouting.process.ValidateFoodPickupProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/routes")
public class RouteController {


     @Autowired
     private ValidateFoodPickupProcess validateFoodPickupProcess;

     @Autowired
     private CalculateRouteDeliveryProcess calculateRouteDeliveryProcess;

     @Autowired
     private AddOrdersSameRestaurant addOrdersSameRestaurant;

     @Autowired
     private AddOrdersDriverProcess addOrdersDriverProcess;



     @GetMapping
     public ResponseEntity<List<RouteBean>> routes () throws IfoodProcessException {

          validateFoodPickupProcess.setNextIfoodProcess(calculateRouteDeliveryProcess);
          calculateRouteDeliveryProcess.setNextIfoodProcess(addOrdersSameRestaurant);
          addOrdersSameRestaurant.setNextIfoodProcess(addOrdersDriverProcess);

          List<Orders> orders = validateFoodPickupProcess.process();
          List<RouteBean> routesCalculated = calculateRouteDeliveryProcess.process(orders);
          Set<ArrayList<RouteBean>> routesSameRestaurantsOrders = addOrdersSameRestaurant.process(routesCalculated);
          addOrdersDriverProcess.process(routesSameRestaurantsOrders);

          return new ResponseEntity<>(routesCalculated, HttpStatus.OK);

     }


}
