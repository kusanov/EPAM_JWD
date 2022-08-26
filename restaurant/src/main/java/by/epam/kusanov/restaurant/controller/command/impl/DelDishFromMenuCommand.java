package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelDishFromMenuCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DelDishFromMenuCommand.class);

    private static final String DISH_ID_REQUEST_ATTR = "dishId";
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_menu";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_main";
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();

        try {
            int dishId = Integer.parseInt(req.getParameter(DISH_ID_REQUEST_ATTR));

            dishService.deleteDishFromMenu(dishId);

            resp.sendRedirect(DelDishFromMenuCommand.REDIRECT_COMMAND_SUCCESS);

        } catch (NumberFormatException e) {
            LOGGER.error("Invalid address to redirect in DelDishFromMenuCommand", e);
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
