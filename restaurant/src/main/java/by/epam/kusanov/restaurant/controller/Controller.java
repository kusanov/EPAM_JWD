package by.epam.kusanov.restaurant.controller;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.dao.exception.ExceptionDAO;
import by.epam.kusanov.restaurant.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Controller extends HttpServlet {

    private static final String REQUEST_PARAMETER_COMMAND = "command";
    private static final String LAST_REQUEST_PARAM = "lastRequest";

    public Controller() {
        super();
    }

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processGetRequest(req, resp);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (ExceptionDAO e) {
            throw new RuntimeException(e);
        }
        return;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            processPostRequest(req, resp);
        } catch (ServiceException | ExceptionDAO e) {
            throw new RuntimeException(e);
        }

        return;
    }

    private void processGetRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException, ExceptionDAO {

        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAMETER_COMMAND);

        executionCommand = provider.getCommand(commandName);
        executionCommand.execute(req,resp);

        req.getSession(true).setAttribute(LAST_REQUEST_PARAM, req.getRequestURI() + "?" + req.getQueryString());
    }

    private void processPostRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ServiceException, ExceptionDAO {

        String commandName;
        Command executionCommand;

        commandName = req.getParameter(REQUEST_PARAMETER_COMMAND);
        executionCommand = provider.getCommand(commandName);

        executionCommand.execute(req,resp);
    }
}

