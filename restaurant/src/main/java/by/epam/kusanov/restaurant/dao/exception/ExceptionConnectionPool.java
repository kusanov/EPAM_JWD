package by.epam.kusanov.restaurant.dao.exception;

public class ExceptionConnectionPool extends Exception {
    private static final long serialVersionUID = 1l;

    public ExceptionConnectionPool(String message, Exception e) {
        super(message, e);
    }
}
