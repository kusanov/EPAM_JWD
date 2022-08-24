package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDishToMenuCommand implements Command {

    private static final String REQUEST_PARAMETER_DISH_NAME = "dishName";
    private static final String REQUEST_PARAMETER_DISH_DESCRIPTION = "dishDescription";
    private static final String REQUEST_PARAMETER_MENU_CATEGORY = "category";
    private static final String REQUEST_PARAMETER_PRICE = "price";
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_menu";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_main";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException, ExceptionDAO {
        Dish dish = new Dish();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();

        String dishName = req.getParameter(REQUEST_PARAMETER_DISH_NAME);
        String dishDescription = req.getParameter(REQUEST_PARAMETER_DISH_DESCRIPTION);
        int categoryId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_MENU_CATEGORY));
        Double price = Double.valueOf(req.getParameter(REQUEST_PARAMETER_PRICE));

        dish.setDishName(dishName);
        dish.setDishDescription(dishDescription);
        dish.setMenuCategory(DAOFactory.getInstance().getDishDAO().getCategoryById(categoryId));
        dish.setPrice(price);

        DishService dishService = serviceFactory.getDishService();

        dishService.addDishToMenu(dish);

        try {
            resp.sendRedirect(AddDishToMenuCommand.REDIRECT_COMMAND_SUCCESS);
        } catch (IOException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }

    }
}
