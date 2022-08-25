package by.epam.kusanov.restaurant.service;

import by.epam.kusanov.restaurant.bean.Invoice;
import by.epam.kusanov.restaurant.bean.Payment;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    void createInvoice(int orderId, int userId, double cost) throws ServiceException;
    List<Invoice> getUserInvoices(int userId) throws ServiceException;
    Payment getPayment(int id) throws ExceptionDAO, ServiceException;
    Map<Integer, String> getPaymentTypes() throws ServiceException;
    void confirmInvoice(int invoiceId, int paymentId) throws ServiceException;


}
