package by.epam.kusanov.restaurant.dao.impl;

import by.epam.kusanov.restaurant.bean.Role;
import by.epam.kusanov.restaurant.bean.User;
import by.epam.kusanov.restaurant.dao.UserDAO;
import by.epam.kusanov.restaurant.dao.connection.ConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String INSERT_USER_SQL = "INSERT INTO restaurant.users  (login, user_password, role_id, user_status) VALUES  (?, ?, ?, ?)";
    private static final String SIGN_IN_SQL = "SELECT * FROM restaurant.users WHERE login = ? AND user_password = ?";
    private static final String GET_ROLE = "SELECT * FROM restaurant.roles WHERE id = ?";
    private static final String GET_USER = "SELECT * FROM restaurant.users WHERE id = ?";
    private static final String GET_USERS = "SELECT * FROM restaurant.users";
    private static final String BLOCK_USER = "UPDATE restaurant.users SET user_status=? where id=?";

    public List<User> getUsers() throws ExceptionDAO {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            List<User> users = new ArrayList<>();
            connection = connectionPool.takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_USERS);
            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                User user =
                new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        DAOFactory.getInstance().getUserDAO().getRoleById(resultSet.getInt(4)),
                       resultSet.getBoolean(5)
                );
                users.add(user);
                System.out.println(user.isActive());
            }
            return users;
        } catch (SQLException e) {
            throw new ExceptionDAO("Error when trying to get users", e);
        } catch (ExceptionConnectionPool e) {
            LOGGER.error("Error to close connection...", e);
            throw new RuntimeException(e);


        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void blockUser(int userId, boolean active) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(BLOCK_USER);
            preparedStatement.setBoolean(1, active);
            preparedStatement.setInt(2, userId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while confirm Order", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while confirm Order", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public User signIn(String login, String password) throws ExceptionDAO {

        PreparedStatement ps = null;
        Connection con = null;

        ResultSet rs = null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SIGN_IN_SQL);
            ps.setString(1, login);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Пользователь с таким логином и паролем не найден.");
                return null;
            }
            if (rs.getBoolean(5)==false) {
                System.out.println("Пользователь с таким логином и паролем заблокирован.");
                return null;
            }

            if (rs.getRow() == 1) {
                System.out.println("Пользователь с таким логином и паролем найден.");
                return new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("user_password"),
                        DAOFactory.getInstance().getUserDAO().getRoleById(rs.getInt("role_id")),
                        rs.getBoolean("user_status"));
            }
        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while authorize User", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while authorize User", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return null;
    }

    @Override
    public void registration(User user) throws ExceptionDAO, ClassNotFoundException {

        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(INSERT_USER_SQL);


            ps.setString(1, user.getLogin());
            ps.setString(2, String.valueOf(user.getPassword()));
            ps.setInt(3, new Role().getRoleId());
            ps.setBoolean(4, true);


            System.out.println(ps);
            // Step 3: Execute the query or update query
            ps.executeUpdate();

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while adding new User", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while adding new User", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }

    }

        @Override
    public Role getRoleById(int roleId) throws ExceptionDAO {

            PreparedStatement ps=null;
            Connection con=null;
            try {
                con = connectionPool.takeConnection();
                ps = con.prepareStatement(GET_ROLE);
            ps.setInt(1, roleId);
//            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
//                System.out.println("Такая роль не найдена.");
                return null;
            }

            if (resultSet.getRow() == 1) {
//                System.out.println("Такая роль найдена.");
                return new Role(
                resultSet.getInt(1),
                resultSet.getString(2));
            }
            } catch (ExceptionConnectionPool e) {
                throw new ExceptionDAO("Error in Connection pool while adding new User", e);
            } catch (SQLException e) {
                throw new ExceptionDAO("Error while adding new User", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }

            return null;
    }

    @Override
    public User getUserById(int userId) throws ExceptionDAO {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_USER);
            ps.setInt(1, userId);
//            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
//                System.out.println("Такой юзер не найден.");
                return null;
            }

            if (resultSet.getRow() == 1) {
//                System.out.println("Такой юзер найден.");
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        getRoleById(resultSet.getInt(4)),
                        resultSet.getBoolean(5));
            }
        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while adding new User", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while adding new User", e);
        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }

        return null;
    }
}


