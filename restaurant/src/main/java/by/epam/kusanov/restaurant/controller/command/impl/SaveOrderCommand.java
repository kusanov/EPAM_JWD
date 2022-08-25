package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class SaveOrderCommand implements Command {

    private static final String REDIRECT_COMMAND = "?command=go_to_main";
    private static final String USER_SESSION_ATTR = "user";
    private static final String ORDER_ID_ATTR = "orderId";

//    private static final String ORDER_ATTR = "order";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException, ExceptionDAO {
        HttpSession session = req.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        Order order = orderService.getOrder((Integer)session.getAttribute(ORDER_ID_ATTR));
        double ordersum=0;
        Map<Dish, Integer> dishes =order.getDishes();
        for(Map.Entry<Dish, Integer> entry : dishes.entrySet()) {
            double price = entry.getKey().getPrice();
            int value = entry.getValue();
            ordersum=ordersum+price*value;
        }
        System.out.println(ordersum);
        orderService.setCost(order.getId(),ordersum);
//        session.removeAttribute(USER_SESSION_ATTR);
        session.removeAttribute(ORDER_ID_ATTR);
        resp.sendRedirect(REDIRECT_COMMAND);
    }
}
