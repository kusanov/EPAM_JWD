package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToRegisterPageCommand implements Command {

    private static final String REGISTRATION_PAGE_URI = "WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(REGISTRATION_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
