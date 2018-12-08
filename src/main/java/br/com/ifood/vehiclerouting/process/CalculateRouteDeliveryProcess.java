package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.response.RouteStatsResponse;
import br.com.ifood.vehiclerouting.entity.Orders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculateRouteDeliveryProcess extends IfoodProcess {

    private IfoodProcess nextIfoodProcess;

    private static final Double UNIT_DISTANCE = 0.1;
    private static final Integer MINUTES_PER_UNIT= 5;

    public List<RouteStatsResponse> process(List<Orders> orders) {

        List<RouteStatsResponse> routes = new ArrayList<>();

        orders.forEach(order -> {

            RouteStatsResponse routesBean = new RouteStatsResponse();
            LocalDateTime date = order.getPickup().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            routesBean.setId(order.getId());
            routesBean.setRestaurantId(order.getRestaurant().getId());
            routesBean.setDistance(Math.sqrt(Math.pow(order.getRestaurant().getLat() - order.getClient().getLat(), 2) + Math.pow(order.getRestaurant().getLon() - order.getClient().getLon(), 2)));
            routesBean.setDistanceTime(MINUTES_PER_UNIT * (routesBean.getDistance() / UNIT_DISTANCE));
            routesBean.setTimeAverageDelivery(date.plusMinutes(routesBean.getDistanceTime().intValue()));
            routesBean.setDelivery(order.getDelivery());

            routes.add(routesBean);
        });

        return routes;
    }

    public void setNextIfoodProcess(IfoodProcess ifoodProcess) {
        nextIfoodProcess = ifoodProcess;
    }

}
