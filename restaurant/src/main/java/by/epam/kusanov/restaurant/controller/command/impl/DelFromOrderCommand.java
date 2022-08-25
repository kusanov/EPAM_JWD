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

public class DelFromOrderCommand implements Command {

    private static final String DISH_ID_ATTR = "dishId";
    private static final String ORDER_ID_ATTR = "orderId";
    private static final String REDIRECT_COMMAND = "?command=get_order";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            int orderId= (int) session.getAttribute(ORDER_ID_ATTR);
            int dishId= Integer.parseInt(req.getParameter(DISH_ID_ATTR));
            orderService.deleteDish(orderId, dishId);

            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (NumberFormatException | ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }
    }
}
