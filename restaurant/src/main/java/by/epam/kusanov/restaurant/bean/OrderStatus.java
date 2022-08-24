package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class OrderStatus implements Serializable {
    private int id;
    private String orderStatus;

    public OrderStatus() {
        this.id = 1;
        this.orderStatus = "new order";
    }

    public OrderStatus(int id) {
        this.id = id;
    }

    public OrderStatus(int id, String orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
