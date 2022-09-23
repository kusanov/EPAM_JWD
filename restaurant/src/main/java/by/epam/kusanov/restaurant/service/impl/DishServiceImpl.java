package by.epam.kusanov.restaurant.service.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.MenuCategory;
import by.epam.kusanov.restaurant.dao.DishDAO;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public class DishServiceImpl implements DishService {
    DAOFactory factory = DAOFactory.getInstance();
    DishDAO dishDAO = factory.getDishDAO();
    @Override
    public List<MenuCategory> getCategories() throws ServiceException {
        try {
            return dishDAO.getCategories();
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find all categories", e);
        }
    }
    @Override
    public MenuCategory getCategoryById(int categoryId) throws ServiceException {
        if (categoryId < 1) {
            return null;
        }

        try {
            return dishDAO.getCategories().get(categoryId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find Category by ID", e);
        }    }


    @Override
    public List<Dish> getMenu() throws ServiceException {
               try {
            return dishDAO.getMenu();
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find Dish by ID", e);
        }
    }

    @Override
    public Dish getDishById(int dishId) throws ServiceException {
        if (dishId < 1) {
            return null;
        }
        try {
            return dishDAO.getDishById(dishId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find Dish by ID", e);
        }
    }

    @Override
    public boolean addOrUpdateDishToMenu(Dish dish) throws ExceptionDAO {
        dishDAO.addOrUpdateDishToMenu(dish);
        return true;
    }

    @Override
    public boolean deleteDishFromMenu(int dishId) throws ServiceException {
        if (dishId < 1) {
            return false;
        }

        try {
            dishDAO.deleteDishFromMenu(dishId);
            return true;
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while remove Dish from Menu", e);
        }
    }

}
