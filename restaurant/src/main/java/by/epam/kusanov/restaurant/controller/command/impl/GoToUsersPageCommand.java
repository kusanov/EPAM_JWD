package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToUsersPageCommand implements Command {

    private static final String USERS_PAGE_URI = "WEB-INF/jsp/users.jsp";
    private static final String USERS_REQ = "users_req";
    private static final String USER_SESSION_ATTR = "user";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        HttpSession session = req.getSession();
session.getAttribute(USER_SESSION_ATTR);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

            req.setAttribute(USERS_REQ, userService.getUsers());
        RequestDispatcher dispatcher = req.getRequestDispatcher(USERS_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
