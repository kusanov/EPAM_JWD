package by.epam.kusanov.restaurant.dao.impl;

import by.epam.kusanov.restaurant.bean.Dish;
import by.epam.kusanov.restaurant.bean.Order;
import by.epam.kusanov.restaurant.bean.OrderStatus;
import by.epam.kusanov.restaurant.dao.OrderDAO;
import by.epam.kusanov.restaurant.dao.connection.ConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_ORDER_BY_ID_SQL = "select * FROM restaurant.orders where id = ?";
    private static final String GET_STATUS = "select * FROM restaurant.statuses where id = ?";
    private static final String DELETE_ITEM_FROM_ORDER_SQL = "delete from restaurant.order_details where order_id = ? and menu_id = ?";
    private static final String GET_CURRENT_ORDER_ID_BY_USER_SQL = "select * FROM restaurant.orders where id = ? and user_id = ?";
    private static final String GET_USER_ORDERS_SQL = "select * FROM restaurant.orders where user_id = ?";
    private static final String GET_KITCHEN_ORDERS_SQL = "select * FROM restaurant.orders where status_id = ?";

    private static final String ADD_DISH_TO_ORDER =
            "insert into restaurant.order_details(order_id,menu_id,quantity,cost) " +
                    "select ? as order_id,? as menu_id,? as quantity," +
                    "(select t.price * quantity as cost from restaurant.menu t where t.id = menu_id)";
    private static final String ADD_ITEM_TO_ORDER_SQL =
            "insert into ishop.order_details(order_id,count,item_id,item_cost)" +
            " select ? as order_id, ? as count, ? as item_id," +
            " (select t.price * count as item_cost from ishop.items t where t.id = item_id)";

    private static final String CREATE_ORDER_QUERY = "INSERT INTO restaurant.orders (user_id, visit_date, status_id) VALUES (?, ?, ?)";
    private static final int GENERATED_KEYS = 1;
    private static final String GET_ORDER_DISHES = "select * FROM restaurant.order_details where order_id = ?";

    private static final String EDIT_ORDER_STATUS = "UPDATE restaurant.orders SET status_id=? where id=?";
    private static final String SET_ORDER_COST = "UPDATE restaurant.orders SET cost=? where id=?";
    private static final String TBL_COLUMN_ORDER_ID = "order_id";


    @Override
    public int createNewOrder(int userId) throws ExceptionDAO {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(3, (new OrderStatus()).getId());
            ps.executeUpdate();

            System.out.println(ps);
            rs = ps.getGeneratedKeys();
            rs.next();

            return rs.getInt(GENERATED_KEYS);

        } catch (SQLException e) {
            throw new ExceptionDAO("Error while adding new Order", e);
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
    @Override
    public List<Order> getUserOrders(int userId) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Order> user_orders = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_USER_ORDERS_SQL);
            preparedStatement.setInt(1, userId);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                user_orders.add(new Order(resultSet.getInt(1),
                        DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt(2)),
                        resultSet.getDate(3),
                        DAOFactory.getInstance().getOrderDAO().getOrderStatus(resultSet.getInt(4)),
                        resultSet.getDouble(5)));
            }
            return user_orders;
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while trying to get user_orders", e);
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);

        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
    @Override
    public List<Order> getKitchenOrders() throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            List<Order> user_orders = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_KITCHEN_ORDERS_SQL);
            preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                user_orders.add(new Order(resultSet.getInt(1),
                        DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt(2)),
                        resultSet.getDate(3),
                        DAOFactory.getInstance().getOrderDAO().getOrderStatus(resultSet.getInt(4)),
                        resultSet.getDouble(5)
                        ));
            }
            return user_orders;
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while trying to get kitchen_orders", e);
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);

        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public void setCost(int orderId,double ordersum) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(SET_ORDER_COST);
            preparedStatement.setDouble(1, ordersum);
            preparedStatement.setInt(2, orderId);

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
    public Order getOrder(int orderId) throws ExceptionDAO {
        PreparedStatement ps = null;
        PreparedStatement ps2;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_ORDER_BY_ID_SQL);
            ps.setInt(1, orderId);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ps2 = con.prepareStatement(GET_ORDER_DISHES);
            ps2.setInt(1, orderId);
            System.out.println(ps2);
            ResultSet rs2 = ps2.executeQuery();

            Map<Dish, Integer> dishes = new HashMap<>();

            while (rs2.next()) {

                Dish dish = DAOFactory.getInstance().getDishDAO().getDishById(rs2.getInt(2));

                int dishCount = rs2.getInt(3);
//                if (dishes.containsKey(dish)) {
//                    dishes.put(dish, dishes.get(dish) + dishCount);
//                } else {
                    dishes.put(dish, dishCount);
//                }
            }
                if (rs.getRow() == 1) {

                    return new Order(
                            rs.getInt(1),
                            DAOFactory.getInstance().getUserDAO().getUserById(rs.getInt(2)),
                            rs.getDate(3),
                            DAOFactory.getInstance().getOrderDAO().getOrderStatus(rs.getInt(4)),
                            dishes);
                }
        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while searching Order", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while searching Order", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return null;
    }
    @Override
    public void addDish(int orderId, int dishId, int quantity) throws ExceptionDAO {

        PreparedStatement ps = null;
        Connection con = null;

        try {
            System.out.println("step1add");
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(ADD_DISH_TO_ORDER);
            ps.setInt(1, orderId);
            ps.setInt(2, dishId);
            ps.setInt(3, quantity);
            System.out.println(ps);

            ps.executeUpdate();
//            System.out.println(ps);

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while adding dish to the order", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while adding dish to the order", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }

    }

    @Override
    public OrderStatus getOrderStatus(int id) throws ExceptionDAO {
        PreparedStatement ps=null;
        Connection con=null;
        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_STATUS);
            ps.setInt(1, id);
            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Такой статус не найден.");
                return null;
            }

            if (resultSet.getRow() == 1) {
                System.out.println("Такой статус найден.");
                return new OrderStatus(
                        resultSet.getInt(1),
                        resultSet.getString(2));
            }
        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while getting OrderStatus", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while getting OrderStatus", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
        return null;
    }


    @Override
    public void deleteDish(int orderId, int dishId) throws ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(DELETE_ITEM_FROM_ORDER_SQL);
            ps.setInt(1, orderId);
            ps.setInt(2, dishId);

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
    public int getCurrentOrderId(int userId) throws ExceptionDAO {

//        PreparedStatement ps = null;
//        Connection con = null;
//        ResultSet rs = null;
        int orderId = 0;
//
//        try {
//            con = connectionPool.takeConnection();
//            ps = con.prepareStatement(GET_CURRENT_ORDER_ID_BY_USER_SQL);
//            ps.setInt(1, userId);
//
//            rs = ps.executeQuery();
//
//            if (rs == null) {
//                return 0;
//            }
//
//            if (rs.next()) {
//                orderId = rs.getInt(TBL_COLUMN_ORDER_ID);
//            }
//
            return orderId;
//
//        } catch (ExceptionConnectionPool e) {
//            throw new ExceptionDAO("Error in Connection pool while getting OrderId", e);
//        } catch (SQLException e) {
//            throw new ExceptionDAO("Error while getting OrderId", e);
//        } finally {
//            connectionPool.closeConnection(con, ps);
//        }
    }


    @Override
    public void confirmOrder(int orderId) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(EDIT_ORDER_STATUS);
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, orderId);

            preparedStatement.executeUpdate();

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while confirm Order", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while confirm Order", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

}
