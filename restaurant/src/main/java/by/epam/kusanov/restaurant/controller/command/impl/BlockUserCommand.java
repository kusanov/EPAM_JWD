package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUserCommand implements Command {
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_users";
    private static final String USER_ID = "userId";
    private static final String ACTIVE = "active";
//    private static final String USER_SESSION_ATTR = "user";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException, ExceptionDAO {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        int userId= Integer.parseInt(req.getParameter(USER_ID));
        System.out.println("userId "+userId);
        boolean active= Boolean.parseBoolean(req.getParameter(ACTIVE));
        System.out.println("active "+active);
        userService.blockUser(userId, active);


        resp.sendRedirect(BlockUserCommand.REDIRECT_COMMAND_SUCCESS);

    }
}
