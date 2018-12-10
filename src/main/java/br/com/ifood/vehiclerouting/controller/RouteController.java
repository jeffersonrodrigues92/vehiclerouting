package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.response.RouteStatsResponse;
import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.process.AddOrdersDriverProcess;
import br.com.ifood.vehiclerouting.process.AddOrdersSameRestaurant;
import br.com.ifood.vehiclerouting.process.CalculateRouteDeliveryProcess;
import br.com.ifood.vehiclerouting.process.ValidateFoodPickupProcess;
import br.com.ifood.vehiclerouting.response.OrderResponse;
import br.com.ifood.vehiclerouting.response.Response;
import br.com.ifood.vehiclerouting.response.RouteResponse;
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
     public ResponseEntity routes(){

          validateFoodPickupProcess.setNextIfoodProcess(calculateRouteDeliveryProcess);
          calculateRouteDeliveryProcess.setNextIfoodProcess(addOrdersSameRestaurant);
          addOrdersSameRestaurant.setNextIfoodProcess(addOrdersDriverProcess);

          List<Order> ordersAvailable = validateFoodPickupProcess.process();
          List<RouteStatsResponse> routesCalculated = calculateRouteDeliveryProcess.process(ordersAvailable);
          Set<ArrayList<RouteStatsResponse>> routesSameRestaurantsOrders = addOrdersSameRestaurant.process(routesCalculated);
          List<OrderResponse> ordersSameRestaurants = addOrdersDriverProcess.process(routesSameRestaurantsOrders);

          if (ordersSameRestaurants.isEmpty()) {
               Response response = new Response();
               response.setMessage("Não foi encontrado nenhum pedido pronto.");
               response.setCode(HttpStatus.NOT_FOUND.value());
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
          }

          RouteResponse routes = new RouteResponse();
          routes.setRoutes(ordersSameRestaurants);

          return new ResponseEntity<>(routes, HttpStatus.OK);
     }


     @GetMapping("/stats")
     public ResponseEntity stats(){

          validateFoodPickupProcess.setNextIfoodProcess(calculateRouteDeliveryProcess);

          List<Order> ordersAvailable = validateFoodPickupProcess.process();
          List<RouteStatsResponse> routesCalculated = calculateRouteDeliveryProcess.process(ordersAvailable);

          if (routesCalculated.isEmpty()) {
               Response response = new Response();
               response.setMessage("Não foi encontrado nenhum pedido pronto.");
               response.setCode(HttpStatus.NOT_FOUND.value());
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
          }

          return new ResponseEntity<>(routesCalculated, HttpStatus.OK);
     }

}
