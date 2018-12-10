package br.com.ifood.vehiclerouting.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JoinColumn(name = "restaurant_id")
    @ManyToOne
    private Restaurant restaurant;
    @NotNull
    @JoinColumn(name = "client_id")
    @ManyToOne
    private Client client;
    @NotNull
    private Date pickup;
    @NotNull
    private Date delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getPickup() {
        return pickup;
    }

    public void setPickup(Date pickup) {
        this.pickup = pickup;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

}


