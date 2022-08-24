package by.epam.kusanov.restaurant.service.impl;

import by.epam.kusanov.restaurant.bean.Invoice;
import by.epam.kusanov.restaurant.bean.Payment;
import by.epam.kusanov.restaurant.dao.PaymentDAO;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    DAOFactory factory = DAOFactory.getInstance();
    PaymentDAO paymentDAO = factory.getPaymentDAO();

    @Override
    public void createInvoice(int orderId, int userId) throws ServiceException {
        try {
            paymentDAO.createInvoice(orderId,userId);

        } catch (ExceptionDAO e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Invoice> getUserInvoices(int userId) throws ServiceException {
        try {
            return paymentDAO.getUserInvoices(userId);
        } catch (ExceptionDAO e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createPayment(int invoiceId, int paymentMethodId) throws ServiceException {
        try {
            paymentDAO.createPayment(invoiceId, paymentMethodId);
        } catch (ExceptionDAO e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Payment getPayment(int id) throws ServiceException {
        try {
           return paymentDAO.getPayment(id);
        } catch (ExceptionDAO e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public Map<Integer, String> getPaymentTypes() throws ServiceException {

        try {
            return paymentDAO.getPaymentTypes();
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while get paymentTypes", e);
        }
    }

    @Override
    public void confirmInvoice(int invoiceId, int paymentId) throws ServiceException {
        try {
            paymentDAO.confirmInvoice(invoiceId, paymentId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while get paymentTypes", e);
        }
    }
}

