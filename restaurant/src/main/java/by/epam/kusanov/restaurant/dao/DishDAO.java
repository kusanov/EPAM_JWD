package by.epam.kusanov.restaurant.dao;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.MenuCategory;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public interface DishDAO {



    List<Dish> getMenu() throws ExceptionDAO;

    List<MenuCategory> getCategories() throws ExceptionDAO;
    MenuCategory getCategoryById(int categoryId) throws ServiceException, ExceptionDAO;
    List<Dish> getDishesByCategory(int categoryID) throws ExceptionDAO;
    Dish getDishById(int dishId) throws ExceptionDAO;

    void addDishToMenu(Dish dish) throws ExceptionDAO;
    void deleteDishFromMenu(int dishId) throws ExceptionDAO;
}
