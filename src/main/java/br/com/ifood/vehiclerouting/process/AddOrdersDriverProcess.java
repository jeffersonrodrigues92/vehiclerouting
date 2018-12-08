package br.com.ifood.vehiclerouting.process;


import br.com.ifood.vehiclerouting.response.RouteStatsResponse;
import br.com.ifood.vehiclerouting.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AddOrdersDriverProcess extends  IfoodProcess{

    private IfoodProcess nextIfoodProcess;

    private static final Integer ORDERS_PER_DRIVER = 3;

    public List<OrderResponse> process(Set<ArrayList<RouteStatsResponse>> routesSameRestaurantsOrders) {

        List<OrderResponse> ordersDelivery = new ArrayList<>();
        List<Long> orderByRestaurants = null;

        for(ArrayList<RouteStatsResponse> route : routesSameRestaurantsOrders){
            orderByRestaurants = new ArrayList<>();
            for(RouteStatsResponse routeBean : route){
                if(orderByRestaurants.size() == ORDERS_PER_DRIVER) {
                    break;
                }
                orderByRestaurants.add(routeBean.getId());
            }
            ordersDelivery.add(new OrderResponse(orderByRestaurants));
        }
        return ordersDelivery;
    }

    @Override
    public void setNextIfoodProcess(IfoodProcess process) {
        nextIfoodProcess = process;
    }
}
