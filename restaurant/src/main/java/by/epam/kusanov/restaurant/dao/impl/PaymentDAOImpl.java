package by.epam.kusanov.restaurant.dao.impl;

import by.epam.kusanov.restaurant.bean.Invoice;
import by.epam.kusanov.restaurant.bean.Payment;
import by.epam.kusanov.restaurant.dao.PaymentDAO;
import by.epam.kusanov.restaurant.dao.connection.ConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionConnectionPool;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.dao.factory.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDAOImpl implements PaymentDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_INVOICE_QUERY = "INSERT INTO restaurant.invoices (order_id,user_id,cost,payment_id) VALUES(?,?,?,?)";
    private static final String GET_PAYMENT_BY_ID = "SELECT * FROM restaurant.payments where id = ?";

    private static final String GET_USER_INVOICES_QUERY = "SELECT * FROM restaurant.invoices where user_id = ?";
    private static final String GET_PAYMENT_TYPES_SQL = "SELECT * FROM restaurant.payments";
    private static final String EDIT_INVOICE_STATUS = "UPDATE restaurant.invoices SET payment_id=? where id=?";



    @Override
    public void createInvoice(int orderId,int userId, double cost) throws ExceptionDAO {
        PreparedStatement preparedStatement = null;
        Connection connection = null;


        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(INSERT_INVOICE_QUERY);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setDouble(3, cost);
            preparedStatement.setInt(4, new Payment().getId());

            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);


        } catch (SQLException e) {
            throw new ExceptionDAO("Error when trying to create invoice", e);
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Invoice> getUserInvoices(int userId) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            List<Invoice> invoices = new ArrayList<>();
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_USER_INVOICES_QUERY);
            preparedStatement.setInt(1, userId);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return null;
            }

            while (resultSet.next()) {
                invoices.add(new Invoice(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        DAOFactory.getInstance().getUserDAO().getUserById(resultSet.getInt(3)),
                                                resultSet.getDouble(4),
                        DAOFactory.getInstance().getPaymentDAO().getPayment(resultSet.getInt(5))));
            }

            return invoices;
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while trying to get user_invoices", e);
        } catch (ExceptionConnectionPool e) {
            throw new RuntimeException(e);

        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Payment getPayment(int id) throws ExceptionDAO {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(GET_PAYMENT_BY_ID);
            ps.setInt(1, id);
            System.out.println(ps);

            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Такой вид оплаты не найден.");
                return null;
            }

            if (rs.getRow() == 1) {
                System.out.println("Такой вид оплаты найден.");
                return new Payment(
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
    public Map<Integer, String> getPaymentTypes() throws ExceptionDAO {
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            st = con.createStatement();

            rs = st.executeQuery(GET_PAYMENT_TYPES_SQL);

            if (rs == null) {
                return null;
            }

            Map<Integer,String> paymentMap = new HashMap<>();

            while (rs.next()) {
                paymentMap.put(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }

            return paymentMap;

        } catch (ExceptionConnectionPool e) {
            throw new ExceptionDAO("Error in Connection pool while find paymentTypes", e);
        } catch (SQLException e) {
            throw new ExceptionDAO("Error while find paymentTypes", e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
    }
    @Override
    public void confirmInvoice(int invoiceId, int paymentId) throws ExceptionDAO {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(EDIT_INVOICE_STATUS);
            preparedStatement.setInt(1, paymentId);
            preparedStatement.setInt(2, invoiceId);
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
}

