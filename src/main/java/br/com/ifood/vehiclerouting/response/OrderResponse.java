package br.com.ifood.vehiclerouting.response;

import java.util.List;

public class OrderResponse {

    private  List<Long> orders;

    public  OrderResponse (List<Long> orders){
        this.orders = orders;
    }

    public List<Long> getOrders() {
        return orders;
    }

    public void setOrders(List<Long> orders) {
        this.orders = orders;
    }
}
