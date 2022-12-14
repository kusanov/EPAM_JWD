package by.epam.kusanov.restaurant.dao.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.MenuCategory;
import by.epam.kusanov.restaurant.dao.DishDAO;
import by.epam.kusanov.restaurant.dao.connection.ConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;
import by.epam.kusanov.restaurant.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDAOImpl implements DishDAO {
    private static final Logger LOGGER = LogManager.getLogger(DishDAOImpl.class);
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String TBL_COLUMN_CATEGORY_ID = "id";
    private static final String TBL_COLUMN_CATEGORY_NAME = "category";
    private static final String TBL_COLUMN_DISH_ID = "id";
    private static final String TBL_COLUMN_DISH_NAME = "dish";
    private static final String TBL_COLUMN_DESCRIPTION = "dish_description";
    private static final String TBL_COLUMN_PRICE = "price";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM restaurant.categories where id = ?";
    private static final String GET_ALL_CATEGORIES_SQL = "select * from restaurant.categories";
    private static final String GET_DISH_BY_ID = "SELECT * FROM restaurant.menu where id = ?";
    private static final String GET_MENU = "SELECT * FROM restaurant.menu";
    private static final String INSERT_DISH_SQL = "INSERT INTO restaurant.menu  (category_id, dish, dish_description, price) VALUES  (?, ?, ?, ?)";
    private static final String DELETE_DISH_FROM_MENU = "delete from restaurant.menu where id = ?";
    private static final String UPDATE_DISH_SQL =
            "UPDATE restaurant.menu SET category_id = ?, dish = ?, dish_description = ?, price = ? WHERE id = ?";



    @Override
    public List<Dish> getMenu() throws ExceptionDAO {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            List<Dish> menu = new ArrayList<>();
            connection = connectionPool.takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_MENU);
            if (resultSet == null) {
                return null;
            }
            while (resultSet.next()) {
                menu.add(new Dish(resultSet.getInt(1),
                        DAOFactory.getInstance().getDishDAO().getCategoryById(resultSet.getInt(2)),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getDouble(5)
                ));
            }
            return menu;
        } catch (SQLException e) {
            throw new ExceptionDAO("Error when trying to get menu", e);
        } catch (ExceptionConnectionPool e) {
            LOGGER.error("Error to close connection...", e);
            throw new RuntimeException(e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }


    @Override
    public Dish getDishById(int dishId) throws ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_DISH_BY_ID);
            ps.setInt(1, dishId);
            System.out.println(ps);
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("?????????? ?????????? ???? ??????????????.");
                return null;
            }
            if (rs.getRow() == 1) {
                System.out.println("?????????? ?????????? ??????????????.");
               return new Dish(
                        rs.getInt(TBL_COLUMN_DISH_ID),
                        DAOFactory.getInstance().getDishDAO().getCategoryById(rs.getInt(2)),
                        rs.getString(TBL_COLUMN_DISH_NAME),
                        rs.getString(TBL_COLUMN_DESCRIPTION),
                        rs.getDouble(TBL_COLUMN_PRICE)
                );
            }
        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while find Dishes", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while find Dishes", e);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return null;
    }

    @Override
    public void addOrUpdateDishToMenu(Dish dish) throws ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();

            if (dish.getDishId() == 0) {
                ps = con.prepareStatement(INSERT_DISH_SQL);
                ps.setInt(1, dish.getMenuCategory().getId());
                ps.setString(2, dish.getDishName());
                ps.setString(3, dish.getDishDescription());
                ps.setDouble(4, dish.getPrice());
            } else {
                ps = con.prepareStatement(UPDATE_DISH_SQL);
            ps.setInt(1, dish.getMenuCategory().getId());
            ps.setString(2, dish.getDishName());
            ps.setString(3, dish.getDishDescription());
            ps.setDouble(4, dish.getPrice());
            ps.setInt(5, dish.getDishId());
        }
            System.out.println(dish.getDishId());
                System.out.println(ps);
                ps.executeUpdate();

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while adding new Dish", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while adding new Dish", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteDishFromMenu(int dishId) throws ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_DISH_FROM_MENU);
            ps.setInt(1, dishId);
            System.out.println(ps);
            ps.executeUpdate();

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while removing the dish from the order", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while removing the dish from the order", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public MenuCategory getCategoryById(int categoryId) throws ServiceException, ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_CATEGORY_BY_ID);
            ps.setInt(1, categoryId);
//            System.out.println(ps);

            rs = ps.executeQuery();

            if (!rs.next()) {
//                System.out.println("?????????? ?????????????????? ???? ??????????????.");
                return null;
            }

            if (rs.getRow() == 1) {
//                System.out.println("?????????? ?????????????????? ??????????????.");
                return new MenuCategory(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while find Dishes", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while find Dishes", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return null;
    }

    @Override
    public List<MenuCategory> getCategories() throws ExceptionDAO {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(GET_ALL_CATEGORIES_SQL);

            if (rs == null) {
                return null;
            }

            List<MenuCategory> categoryList = new ArrayList<>();
            while (rs.next()) {
                categoryList.add(new MenuCategory(
                        rs.getInt(TBL_COLUMN_CATEGORY_ID),
                        rs.getString(TBL_COLUMN_CATEGORY_NAME)
                ));
            }
            return categoryList;

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while find Categories", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while find Categories", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
    }
}
