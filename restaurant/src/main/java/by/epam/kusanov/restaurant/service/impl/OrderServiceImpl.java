package by.epam.kusanov.restaurant.service.impl;

import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.OrderStatus;
import by.epam.kusanov.restaurant.dao.OrderDAO;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    DAOFactory factory = DAOFactory.getInstance();
    OrderDAO orderDAO = factory.getOrderDAO();
    @Override
    public int createNewOrder(int userId) throws ServiceException {

        try {
            return orderDAO.createNewOrder(userId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find create Order", e);
        }
    }

    @Override
    public boolean addDish(int orderId, int dishId, int quantity) throws ServiceException {

        if (orderId < 1 || dishId < 1 || quantity < 1) {
            return false;
        }

        try {
            orderDAO.addDish(orderId, dishId, quantity);
            return true;
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while adding Dish to Order", e);
        }
    }

    @Override
    public boolean deleteDish(int orderId, int itemId) throws ServiceException {

        if (orderId < 1 || itemId < 1) {
            return false;
        }

        try {
            orderDAO.deleteDish(orderId, itemId);
            return true;
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while remove Dish from Order", e);
        }
    }

    @Override
    public Order getOrder(int orderId) throws ServiceException {

        if (orderId < 1) {
            return null;
        }

        try {
            return orderDAO.getOrder(orderId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while calling getOrder()", e);
        }
    }

    @Override
    public OrderStatus getOrderStatus(int id) throws ServiceException, ExceptionDAO {

        return orderDAO.getOrderStatus(id);
    }

    @Override
    public List<Order> getUserOrders(int userId) throws ServiceException, ExceptionDAO {

        return orderDAO.getUserOrders(userId);
    }


    @Override
    public void confirmOrder(int order) throws ServiceException {

        try {
            orderDAO.confirmOrder(order);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while confirm Order", e);
        }
    }

    @Override
    public List<Order> getKitchenOrders() throws ExceptionDAO {
        return orderDAO.getKitchenOrders();    }
}
