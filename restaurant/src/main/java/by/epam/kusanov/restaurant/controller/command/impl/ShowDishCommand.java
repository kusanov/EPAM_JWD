package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowDishCommand implements Command {

    private static final String REQUEST_PARAMETER_DISH_ID = "dishId";
    private static final String DISH_ATTR = "dish";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_catalog";
    private static final String DISH_PAGE_URI = "WEB-INF/jsp/new_dish.jsp";
    private static final String REVIEW_LIST_ATTR = "reviewList";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();

        try {
            int dishId = Integer.parseInt(req.getParameter(REQUEST_PARAMETER_DISH_ID));
            System.out.println(dishId);
            dishService.getDishById(dishId);



            req.setAttribute(DISH_ATTR, dishService.getDishById(dishId));
            RequestDispatcher dispatcher = req.getRequestDispatcher(DISH_PAGE_URI);
            dispatcher.forward(req, resp);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
    }
}
