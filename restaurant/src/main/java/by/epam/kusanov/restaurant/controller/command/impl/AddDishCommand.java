package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.User;
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
        int orderId;
        Order order;
//        int quantity=5;
//        int dishId=3;

        if (session.getAttribute(USER_SESSION_ATTR) == null) {
            resp.sendRedirect(REDIRECT_LOGIN_PAGE);
            return;
        }

        try {
            User authorizedUser = (User) session.getAttribute(USER_SESSION_ATTR);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            System.out.println("step0");

            int dishId = Integer.parseInt(req.getParameter(DISH_ID_REQUEST_ATTR));
            System.out.println(dishId);
            int quantity = Integer.parseInt(req.getParameter(DISH_QUANTITY_REQUEST_ATTR));
            System.out.println(quantity);

            System.out.println("step1");

            if (req.getAttribute(ORDER_ID_ATTR) == null) {
                System.out.println("step2");
                orderId = orderService.createNewOrder(authorizedUser.getUserId());
                req.setAttribute(ORDER_ID_ATTR, orderId);
            }
            else {
                orderId = (int) req.getAttribute(ORDER_ID_ATTR);
            }
            System.out.println("step3");

            orderService.addDish(orderId,dishId,quantity);

                resp.sendRedirect(AddDishCommand.REDIRECT_COMMAND_SUCCESS);
            } catch (IOException e) {
                resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
            }
    }
}
