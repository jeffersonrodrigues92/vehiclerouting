package br.com.ifood.vehiclerouting.process;

import br.com.ifood.vehiclerouting.bean.RouteBean;
import br.com.ifood.vehiclerouting.entity.Orders;
import br.com.ifood.vehiclerouting.exception.IfoodProcessException;
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

    public List<RouteBean> process(List<Orders> orders) throws IfoodProcessException {

        List<RouteBean> routes = new ArrayList<>();

        orders.forEach(order -> {

            RouteBean routesBean = new RouteBean();
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
