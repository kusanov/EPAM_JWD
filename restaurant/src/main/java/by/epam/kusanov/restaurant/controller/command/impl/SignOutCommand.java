package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements Command {

    private static final String REDIRECT_COMMAND = "?command=go_to_main";
    private static final String USER_SESSION_ATTR = "user";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute(USER_SESSION_ATTR);
        resp.sendRedirect(REDIRECT_COMMAND);
    }
}
