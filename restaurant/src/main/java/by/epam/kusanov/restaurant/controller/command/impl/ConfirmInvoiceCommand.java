package by.epam.kusanov.restaurant.controller.command.impl;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmInvoiceCommand implements Command {
    private static final String REDIRECT_COMMAND_SUCCESS = "?command=go_to_user_orders";
    private static final String REDIRECT_COMMAND = "?command=go_to_menu";

    private static final String INVOICE_ID = "invoiceId";
    private static final String PAYMENT_ID = "paymentId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            System.out.println("0");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PaymentService paymentService = serviceFactory.getPaymentService();
            int invoiceId= Integer.parseInt(req.getParameter(INVOICE_ID));
            int paymentId= Integer.parseInt(req.getParameter(PAYMENT_ID));
            paymentService.confirmInvoice(invoiceId,paymentId);
            resp.sendRedirect(ConfirmInvoiceCommand.REDIRECT_COMMAND_SUCCESS);
        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }
    }
}
