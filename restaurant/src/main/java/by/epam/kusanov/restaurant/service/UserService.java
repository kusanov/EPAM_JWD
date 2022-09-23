package by.epam.kusanov.restaurant.service;

import by.epam.kusanov.restaurant.bean.Role;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User signIn(String login, String password) throws ExceptionDAO, ServiceException;
    void signOut(String login);
    boolean registration(User user);
    Role getRoleById(int roleId) throws ServiceException;
    List<User> getUsers()  throws ServiceException;


    void blockUser(int userId,boolean active) throws ServiceException, ExceptionDAO;

}

