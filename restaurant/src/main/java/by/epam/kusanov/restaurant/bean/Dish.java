package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class Dish implements Serializable {
    public static final long serialVersionUID = 1L;
    private int dishId;
    private MenuCategory menuCategory;
    private String dishName;
    private String dishDescription;
    private double price;

    public Dish() {    }

    public Dish(int dishId) {
        this.dishId = dishId;
    }

    public Dish(int dishId, MenuCategory menuCategory, String dishName, String dishDescription, double price) {
        this.dishId = dishId;
        this.menuCategory = menuCategory;
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.price = price;
    }
    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(MenuCategory menuCategory) {
        this.menuCategory = menuCategory;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
