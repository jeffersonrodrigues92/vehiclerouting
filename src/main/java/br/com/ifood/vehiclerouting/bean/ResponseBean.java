package br.com.ifood.vehiclerouting.bean;

import java.util.List;

public class ResponseBean {

    private List<RoutesBean> routes;

    public List<RoutesBean> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesBean> routes) {
        this.routes = routes;
    }
}
