package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToNewDishPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GoToNewDishPageCommand.class);

    private static final String NEW_DISH_PAGE_URI = "WEB-INF/jsp/dish_info.jsp";
    private static final String DISH = "dish";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dish dish = new Dish();
        req.setAttribute(DISH, dish);
        RequestDispatcher dispatcher = req.getRequestDispatcher(NEW_DISH_PAGE_URI);
        try {
            dispatcher.forward(req, resp);
        } catch (IOException e) {
            LOGGER.error("Invalid address to redirect in GoToNewDishPageCommand", e);
        }
    }
}
