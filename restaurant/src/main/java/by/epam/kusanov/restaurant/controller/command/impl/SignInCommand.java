package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements Command {

    private static final String REQUEST_PARAMETER_LOGIN = "login";
    private static final String REQUEST_PARAMETER_PASSWORD = "password";
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_main&login=success";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_login&login=fail";
    private static final String USER_ORDERS = "user_orders";
    private static final String USER_INVOICES = "user_invoices";
    private static final String USER_SESSION_ATTR = "user";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter(REQUEST_PARAMETER_LOGIN);
        String password = req.getParameter(REQUEST_PARAMETER_PASSWORD);

        User authorizedUser;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        OrderService orderService = serviceFactory.getOrderService();
        PaymentService paymentService = serviceFactory.getPaymentService();
        HttpSession session = req.getSession(true);

        try {
            authorizedUser = userService.signIn(login, password);
            System.out.println(authorizedUser);
            if (authorizedUser == null) {
                resp.sendRedirect(REDIRECT_COMMAND_ERROR);
                return;
            }
            session.setAttribute(USER_SESSION_ATTR, authorizedUser);

//            List<Order> userOrders = orderService.getUserOrders(authorizedUser.getUserId());
//
//            if (userOrders != null) {
//                session.setAttribute(USER_ORDERS, userOrders);
//            }
//            List<Invoice> userInvoices = paymentService.getUserInvoices(authorizedUser.getUserId());
//
//            if (userInvoices != null) {
//                session.setAttribute(USER_INVOICES, userInvoices);
//            }

            resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);

        } catch (ServiceException | ExceptionDAO e) {
            // log
            resp.sendRedirect(REDIRECT_COMMAND_ERROR);

        }
    }
}
