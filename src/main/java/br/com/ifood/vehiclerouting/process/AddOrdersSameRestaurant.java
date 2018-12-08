package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.response.RouteStatsResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddOrdersSameRestaurant extends IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    public Set<ArrayList<RouteStatsResponse>> process(List<RouteStatsResponse> routesCalculated) {

        Set<ArrayList<RouteStatsResponse>> orderSameRestaurants = new HashSet<>();
        ArrayList<RouteStatsResponse> ordersSameRestaurantsId = new ArrayList<>();

        //ADICIONANDO EM UM ARRAY TODOS OS PEDIDOS DO MESMO RESTAURANTE
        for(RouteStatsResponse order : routesCalculated){
            ordersSameRestaurantsId = new ArrayList<>();
            for(RouteStatsResponse ordersSameRestaurant : routesCalculated){
                if(order.getRestaurantId() == ordersSameRestaurant.getRestaurantId()){
                    ordersSameRestaurantsId.add(ordersSameRestaurant);
                }
            }
            //ORDERNANDO OS PEDIDOS PARA A MENOR DISTANCIA ENTRE RESTAURANTE E CLIENTES
            ordersSameRestaurantsId.sort(new Comparator<RouteStatsResponse>() {
                public int compare(RouteStatsResponse routeBean1, RouteStatsResponse routeBean2) {
                    return routeBean1.getDistance().compareTo(routeBean2.getDistance());
                }
            });

            //ADICIONANDO LISTA DE PEDIDOS DE UM UNICO RESTAURANTE NA LISTA PAI.
            orderSameRestaurants.add(ordersSameRestaurantsId);
        }
        return orderSameRestaurants;
    }

    public void setNextIfoodProcess(IfoodProcess process) {
        nextIfoodProcess = process;
    }

}
