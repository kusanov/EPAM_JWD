package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoToMenuCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GoToMenuCommand.class);
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_main";
    private static final String MENU_PAGE_URI = "WEB-INF/jsp/menu.jsp";
    private static final String MENU_REQ = "menu_req";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();

        try {
            req.setAttribute(MENU_REQ, dishService.getMenu());
            RequestDispatcher dispatcher = req.getRequestDispatcher(MENU_PAGE_URI);
            dispatcher.forward(req, resp);
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid address to redirect in GoToMenuCommand", e);
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
