package by.epam.kusanov.restaurant.controller;

import by.epam.kusanov.restaurant.dao.connection.ConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionConnectionPool;
import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import by.epam.kusanov.restaurant.service.factory.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RestaurantCtxListener implements ServletContextListener {
    private static final String MENU_APP = "menu_app";
    private static final String MENU_CATEGORIES_APP = "menu_categories_app";
    private static final String USERS_APP = "users_app";
    private static final String ORDERS_APP = "orders_app";
    private static final String PAYMENT_TYPES_MAP_ATTR = "payments_app";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DishService dishService = serviceFactory.getDishService();
//        UserService userService = serviceFactory.getUserService();
//        OrderService orderService = serviceFactory.getOrderService();
        PaymentService paymentService = serviceFactory.getPaymentService();

        try {
            ConnectionPool.getInstance().initPoolData();
            servletContext.setAttribute(MENU_APP, dishService.getMenu());
            servletContext.setAttribute(MENU_CATEGORIES_APP, dishService.getCategories());
            servletContext.setAttribute(PAYMENT_TYPES_MAP_ATTR, paymentService.getPaymentTypes());
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
