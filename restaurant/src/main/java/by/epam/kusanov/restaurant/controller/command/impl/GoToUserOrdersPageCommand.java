package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.Invoice;
import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoToUserOrdersPageCommand implements Command {
    private static final String ORDERS_PAGE_URI = "WEB-INF/jsp/orders_invoices.jsp";
    private static final String MENU_ATTR = "menu";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_login";
    private static final String USER_ORDERS = "user_orders";
    private static final String USER_INVOICES = "user_invoices";
    private static final String USER_SESSION_ATTR = "user";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException, ExceptionDAO {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = req.getSession(true);
        OrderService orderService = serviceFactory.getOrderService();
        PaymentService paymentService = serviceFactory.getPaymentService();
        if (session.getAttribute(USER_SESSION_ATTR) == null) {

            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            return;
        }
                User authorizedUser = (User) session.getAttribute(USER_SESSION_ATTR);

                List<Order> userOrders = orderService.getUserOrders(authorizedUser.getUserId());
        if (userOrders != null) {
            req.setAttribute(USER_ORDERS, userOrders);
        }

                List<Invoice> userInvoices = paymentService.getUserInvoices(authorizedUser.getUserId());
        if (userInvoices != null) {
            req.setAttribute(USER_INVOICES, userInvoices);
        }
                RequestDispatcher dispatcher = req.getRequestDispatcher(ORDERS_PAGE_URI);
                dispatcher.forward(req, resp);

    }
}