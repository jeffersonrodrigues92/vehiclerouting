package br.com.ifood.vehiclerouting.response;

import java.util.List;

public class RouteResponse {

    private List<OrderResponse> routes ;

    public List<OrderResponse> getRoutes() {
        return routes;
    }

    public void setRoutes(List<OrderResponse> routes) {
        this.routes = routes;
    }
}
