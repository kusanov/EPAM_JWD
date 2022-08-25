package by.epam.kusanov.restaurant.dao;

import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.OrderStatus;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;

import java.util.List;

public interface OrderDAO {
    int createNewOrder(int userId) throws ExceptionDAO;

    void addDish(int orderId, int dishId, int quantity) throws ExceptionDAO;

    void deleteDish(int orderId, int itemId) throws ExceptionDAO;
    Order getOrder(int orderId) throws ExceptionDAO;
    int getCurrentOrderId(int userId) throws ExceptionDAO;
    void confirmOrder(int orderId) throws ExceptionDAO;

    List<Order> getUserOrders(int userId) throws ExceptionDAO;

    OrderStatus getOrderStatus(int id) throws ExceptionDAO;

    List<Order> getKitchenOrders() throws ExceptionDAO;

    void setCost(int orderId,double ordersum) throws ExceptionDAO;
}
