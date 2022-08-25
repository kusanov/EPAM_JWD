package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddDishCommand implements Command {

    private static final String REDIRECT_COMMAND_SUCCESS = "?command=get_order";
    private static final String REDIRECT_LOGIN_PAGE = "?command=go_to_login";

    private static final String USER_SESSION_ATTR = "user";
    private static final String ORDER_ID_ATTR = "orderId";
    private static final String DISH_ID_REQUEST_ATTR = "dishId";
    private static final String DISH_QUANTITY_REQUEST_ATTR = "quantity";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {

        HttpSession session = req.getSession(true);

        if (session.getAttribute(USER_SESSION_ATTR) == null) {
            resp.sendRedirect(REDIRECT_LOGIN_PAGE);
            return;
        }

        try {

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();

            int dishId = Integer.parseInt(req.getParameter(DISH_ID_REQUEST_ATTR));
            int quantity = Integer.parseInt(req.getParameter(DISH_QUANTITY_REQUEST_ATTR));

            int orderId = (int) session.getAttribute(ORDER_ID_ATTR);

            orderService.addDish(orderId,dishId,quantity);

                resp.sendRedirect(AddDishCommand.REDIRECT_COMMAND_SUCCESS);
            } catch (IOException e) {
                resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
            }
    }
}
