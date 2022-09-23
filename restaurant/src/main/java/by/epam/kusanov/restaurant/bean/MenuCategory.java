package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class MenuCategory implements Serializable {
    private int categoryId;
    private String categoryName;

    public MenuCategory() {
    }

    public MenuCategory(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    public int getId() {
        return categoryId;
    }

    public void setId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
