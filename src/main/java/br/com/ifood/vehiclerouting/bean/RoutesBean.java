package br.com.ifood.vehiclerouting.bean;

import java.util.List;

public class RoutesBean {

    private List<Long> orders;

    public List<Long> getOrders() {
        return orders;
    }

    public void setOrders(List<Long> orders) {
        this.orders = orders;
    }
}
