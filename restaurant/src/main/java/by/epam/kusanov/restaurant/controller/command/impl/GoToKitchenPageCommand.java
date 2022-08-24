package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToKitchenPageCommand implements Command {
    private static final String KITCHEN_PAGE_URI = "WEB-INF/jsp/kitchen.jsp";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_login";
    private static final String KITCHEN_ORDERS = "kitchen_orders";
    private static final String USER_SESSION_ATTR = "user";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ExceptionDAO {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = req.getSession(true);
        OrderService orderService = serviceFactory.getOrderService();
        if (session.getAttribute(USER_SESSION_ATTR) == null) {

            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            return;
        }

        List<Order> kitchenOrders = orderService.getKitchenOrders();
//
        if (kitchenOrders != null) {
            req.setAttribute(KITCHEN_ORDERS, kitchenOrders);


            RequestDispatcher dispatcher = req.getRequestDispatcher(KITCHEN_PAGE_URI);
            dispatcher.forward(req, resp);

        }
    }
}