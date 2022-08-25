package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConfirmOrderCommand implements Command {
    private static final String CATALOG_PAGE_URI = "WEB-INF/jsp/orders_invoices.jsp";
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_kitchen";
    private static final String REDIRECT_COMMAND_ERROR = "?command=go_to_login";

    private static final String ORDER_ID_ATTR = "orderId";
    private static final String USER_SESSION_ATTR = "user";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        if (session.getAttribute(USER_SESSION_ATTR) == null) {

            resp.sendRedirect(REDIRECT_COMMAND_ERROR);
            return;
        }
        int orderId;

        try {
            orderId = Integer.parseInt(req.getParameter(ORDER_ID_ATTR));

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            PaymentService paymentService = serviceFactory.getPaymentService();

            orderService.confirmOrder(orderId);

            paymentService.createInvoice(orderId,
                    orderService.getOrder(orderId).getUser().getUserId(),
                    orderService.getOrder(orderId).getCost());

            resp.sendRedirect(ConfirmOrderCommand.REDIRECT_COMMAND_SUCCESS);

        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND_SUCCESS);
        }
    }
}
