package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
    private int id;
    private User user;
    private Date date;
    private OrderStatus orderStatus;
    private double cost;
    private Map<Dish, Integer> dishes = new HashMap<>();

    public Order(int id, User user, Date date, OrderStatus orderStatus, Map<Dish, Integer> dishes) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.orderStatus = orderStatus;
        this.dishes = dishes;
    }public Order(int id, User user, Date date, OrderStatus orderStatus, double cost) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.orderStatus = orderStatus;
        this.dishes = dishes;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        this.dishes = dishes;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
