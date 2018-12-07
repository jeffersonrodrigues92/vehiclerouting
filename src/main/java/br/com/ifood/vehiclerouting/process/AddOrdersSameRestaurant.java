package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.bean.RouteBean;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddOrdersSameRestaurant extends IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    public Set<ArrayList<RouteBean>> process(List<RouteBean> routesCalculated) throws IfoodProcessException {

        Set<ArrayList<RouteBean>> orderSameRestaurants = new HashSet<>();
        ArrayList<RouteBean> ordersSameRestaurantsId = new ArrayList<>();

        //ADICIONANDO EM UM ARRAY TODOS OS PEDIDOS DO MESMO RESTAURANTE
        for(RouteBean order : routesCalculated){
            ordersSameRestaurantsId = new ArrayList<>();
            for(RouteBean ordersSameRestaurant : routesCalculated){
                if(order.getRestaurantId() == ordersSameRestaurant.getRestaurantId()){
                    ordersSameRestaurantsId.add(ordersSameRestaurant);
                }
            }
            //ORDERNANDO OS PEDIDOS PARA A MENOR DISTANCIA ENTRE RESTAURANTE E CLIENTES
            ordersSameRestaurantsId.sort(new Comparator<RouteBean>() {
                public int compare(RouteBean routeBean1, RouteBean routeBean2) {
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
