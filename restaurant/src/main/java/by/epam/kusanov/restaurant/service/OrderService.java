package by.epam.kusanov.restaurant.service;

import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.OrderStatus;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    int createNewOrder(int userId) throws ServiceException;
    boolean addDish(int orderId, int dishId, int quantity) throws ServiceException;

    boolean deleteDish(int orderId, int dishId) throws ServiceException;
    Order getOrder(int orderId) throws ServiceException;
    OrderStatus getOrderStatus(int id) throws ServiceException, ExceptionDAO;
    List<Order> getUserOrders(int userId) throws ServiceException, ExceptionDAO;
    void confirmOrder(int order) throws ServiceException;


    List<Order> getKitchenOrders() throws ExceptionDAO;

    void setCost(int orderId,double ordersum) throws ExceptionDAO;
}
