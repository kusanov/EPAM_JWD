package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private static final String REQUEST_PARAMETER_LOGIN = "login";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_main&register=success";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_register&register=error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = new User();

        String login = req.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = req.getParameter(REQUEST_PARAMETER_PASSWORD);

        user.setLogin(login);
        user.setPassword(password);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        userService.registration(user);

        try {
            resp.sendRedirect(RegistrationCommand.REDIRECT_COMMAND_SUCCESS);
        } catch (IOException e) {
            LOGGER.error("Invalid address to redirect in RegistrationCommand", e);
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }

    }
}
