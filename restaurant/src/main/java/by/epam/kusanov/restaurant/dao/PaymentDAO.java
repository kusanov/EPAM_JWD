package by.epam.kusanov.restaurant.dao;

import by.epam.kusanov.restaurant.bean.Invoice;
import by.epam.kusanov.restaurant.bean.Payment;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;

import java.util.List;
import java.util.Map;

public interface PaymentDAO {
    void createInvoice(int orderId, int userId, double cost) throws ExceptionDAO;
    List<Invoice> getUserInvoices(int userId) throws ExceptionDAO;
    Payment getPayment(int id) throws ExceptionDAO;
    Map<Integer, String> getPaymentTypes() throws ExceptionDAO;
    void confirmInvoice(int invoiceId, int paymentId) throws ExceptionDAO;
}
