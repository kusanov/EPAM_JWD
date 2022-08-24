package by.epam.kusanov.restaurant.dao.factory;

import by.epam.kusanov.restaurant.dao.DishDAO;
import by.epam.kusanov.restaurant.dao.OrderDAO;
import by.epam.kusanov.restaurant.dao.PaymentDAO;
import by.epam.kusanov.restaurant.dao.UserDAO;
import by.epam.kusanov.restaurant.dao.impl.DishDAOImpl;
import by.epam.kusanov.restaurant.dao.impl.OrderDAOImpl;
import by.epam.kusanov.restaurant.dao.impl.PaymentDAOImpl;
import by.epam.kusanov.restaurant.dao.impl.UserDAOImpl;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();
    private final DishDAO dishDAOImpl = new DishDAOImpl();
    private final UserDAO userDAOImpl = new UserDAOImpl();
    private final OrderDAO orderDAOImpl = new OrderDAOImpl();
    private final PaymentDAO paymentDAOImpl = new PaymentDAOImpl();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }
    public DishDAO getDishDAO(){
        return dishDAOImpl;
    }
    public UserDAO getUserDAO(){
        return userDAOImpl;
    }
    public OrderDAO getOrderDAO() {return  orderDAOImpl;}
    public PaymentDAO getPaymentDAO() {return  paymentDAOImpl;}

}

