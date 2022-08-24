package by.epam.kusanov.restaurant.dao;

import by.epam.kusanov.restaurant.bean.Role;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;

import java.util.List;

public interface UserDAO {
    User signIn(String login, String password) throws ExceptionDAO;

    void registration(User user) throws ExceptionDAO, ClassNotFoundException;
    Role getRoleById(int roleId) throws ExceptionDAO;

    User getUserById(int userId) throws ExceptionDAO;
    List<User> getUsers()  throws ExceptionDAO;

    void blockUser(int userId,boolean active) throws ExceptionDAO;
}
