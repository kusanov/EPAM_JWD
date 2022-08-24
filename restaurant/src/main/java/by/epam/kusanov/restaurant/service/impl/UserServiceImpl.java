package by.epam.kusanov.restaurant.service.impl;

import by.epam.kusanov.restaurant.bean.Role;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.dao.UserDAO;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.UserService;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    DAOFactory factory = DAOFactory.getInstance();
    UserDAO userDAO = factory.getUserDAO();
    @Override
    public User signIn(String login, String password) throws ServiceException {


        if (login.equals("") || password.equals("")) {
            throw new ServiceException("Error while signIn User. Login or Password is null");
        }

        UserDAO userDAO = factory.getUserDAO();

        try {
            return userDAO.signIn(login, password);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while signIn User", e);
        }
    }

    @Override
    public void signOut(String login) {
    }

    @Override
    public boolean registration(User user) {
        if (user.getLogin().equals("") || user.getPassword().equals("")){
            return false;
        }

        try {
            userDAO.registration(user);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return true;
    }

    @Override
    public Role getRoleById(int roleId) throws ServiceException {

        if (roleId < 1) {
            return null;
        }

        try {
            return userDAO.getRoleById(roleId);
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while find Role by ID", e);
        }    }

    @Override
    public List<User> getUsers() throws ServiceException {
        try {
            return userDAO.getUsers();
        } catch (ExceptionDAO e) {
            throw new ServiceException("Error while getting users", e);
        }
    }

    @Override
    public void blockUser(int userId, boolean active) throws ExceptionDAO {
        userDAO.blockUser(userId,active);
    }
}

