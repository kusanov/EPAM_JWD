package by.epam.kusanov.restaurant.service.factory;

import by.epam.kusanov.restaurant.service.DishService;
import by.epam.kusanov.restaurant.service.OrderService;
import by.epam.kusanov.restaurant.service.PaymentService;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.impl.DishServiceImpl;
import by.epam.kusanov.restaurant.service.impl.OrderServiceImpl;
import by.epam.kusanov.restaurant.service.impl.PaymentServiceImpl;
import by.epam.kusanov.restaurant.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final DishService dishService = new DishServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final PaymentService paymentService = new PaymentServiceImpl();

    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance;
    }
    public UserService getUserService(){
        return userService;
    }
    public DishService getDishService() {
        return dishService;
    }

    public OrderService getOrderService() {return orderService;}
    public PaymentService getPaymentService() {return paymentService;}
}

