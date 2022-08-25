package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetOrderCommand implements Command {

    private static final String ORDER_PAGE_URI = "WEB-INF/jsp/order.jsp";
    private static final String REDIRECT_COMMAND = "?command=get_order";

    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_login";
    private static final String ORDER_ID_ATTR = "orderId";
    private static final String ORDER_ATTR = "order";
//    private static final String ORDER_REQ_ATTR = "order_req";
    private static final String USER_ORDERS = "user_orders";
    private static final String USER_SESSION_ATTR = "user";
    private static final String USER_ID_SESSION_ATTR = "userId";
    private static final String MENU_ATTR = "menu";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException {
        int orderId;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session = req.getSession(true);
        try {
            if (session.getAttribute(USER_SESSION_ATTR) != null) {
                int userId = ((User) session.getAttribute(USER_SESSION_ATTR)).getUserId();
                session.setAttribute(USER_ID_SESSION_ATTR, userId);
                System.out.println(userId);
            }
            else {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            return;
            }
//
                if (session.getAttribute(ORDER_ID_ATTR) == null) {
                    orderId = orderService.createNewOrder((Integer)session.getAttribute(USER_ID_SESSION_ATTR));
                session.setAttribute(ORDER_ID_ATTR,orderId);
                    System.out.println(orderId);
                }
//                req.getAttribute(ORDER_ID_ATTR);

//            if (session.getAttribute(ORDER_ATTR) == null) {
//                order = orderService.getOrder((Integer) session.getAttribute(ORDER_ID_ATTR));
//                session.setAttribute(ORDER_ATTR, order);}
            req.setAttribute(ORDER_ATTR,orderService.getOrder((Integer)session.getAttribute(ORDER_ID_ATTR)));


            List<Dish> menu = dishService.getMenu();
                    req.setAttribute(MENU_ATTR, menu);

                    RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_PAGE_URI);
                    dispatcher.forward(req, resp);


        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
        }
    }
}