package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.bean.RoutesBean;
import br.com.ifood.vehiclerouting.bean.ResponseBean;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
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

@RestController
@RequestMapping("/routes")
public class RouteController {


     @Autowired
     private ValidateFoodPickupProcess validateFoodPickupProcess;

     @Autowired
     private CalculateRouteDeliveryProcess calculateRouteDeliveryProcess;

     @GetMapping
     public ResponseEntity<ResponseBean> routes () throws IfoodProcessException {

          validateFoodPickupProcess.setNextIfoodProcess(calculateRouteDeliveryProcess);

          List<Orders> orders = validateFoodPickupProcess.process();
          List<RoutesBean> routes = calculateRouteDeliveryProcess.process(orders);


          ResponseBean teste = new ResponseBean();
          List<RoutesBean> routesBeansList = new ArrayList<>();
          routesBeansList.addAll(routes);

          teste.setRoutes(routesBeansList);

          return new ResponseEntity<>(teste, HttpStatus.OK);

     }


}
