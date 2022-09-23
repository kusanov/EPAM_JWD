package by.epam.kusanov.restaurant.service;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.MenuCategory;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public interface DishService {

    List<MenuCategory> getCategories() throws ServiceException;
    MenuCategory getCategoryById(int categoryId) throws ServiceException;
    List<Dish> getMenu() throws ServiceException;
    Dish getDishById(int dishId) throws ServiceException;
    boolean addOrUpdateDishToMenu(Dish dish) throws ExceptionDAO, ServiceException;
    boolean deleteDishFromMenu(int dishId) throws ExceptionDAO, ServiceException;
}
