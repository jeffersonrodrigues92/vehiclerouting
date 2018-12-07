package br.com.ifood.vehiclerouting.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class RouteBean {

    private Long id;
    private Long restaurantId;
    private Double distance;
    private LocalDateTime timeAverageDelivery;
    private Double distanceTime;
    private Date delivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDistanceTime() {
        return distanceTime;
    }

    public LocalDateTime getTimeAverageDelivery() {
        return timeAverageDelivery;
    }

    public void setTimeAverageDelivery(LocalDateTime timeAverageDelivery) {
        this.timeAverageDelivery = timeAverageDelivery;
    }

    public void setDistanceTime(Double distanceTime) {
        this.distanceTime = distanceTime;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }
}
