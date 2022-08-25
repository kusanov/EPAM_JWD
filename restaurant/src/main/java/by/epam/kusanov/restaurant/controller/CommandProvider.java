package by.epam.kusanov.restaurant.controller;

import by.epam.kusanov.restaurant.controller.command.Command;
import by.epam.kusanov.restaurant.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());
        repository.put(CommandName.ADD_DISH, new AddDishCommand());
        repository.put(CommandName.ADD_TO_MENU, new AddDishToMenuCommand());
        repository.put(CommandName.BLOCK_USER, new BlockUserCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequestCommand());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
        repository.put(CommandName.GO_TO_LOGIN, new GoToLoginPageCommand());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegisterPageCommand());
        repository.put(CommandName.GO_TO_MAIN, new GoToMainPageCommand());
        repository.put(CommandName.GO_TO_KITCHEN, new GoToKitchenPageCommand());
        repository.put(CommandName.GO_TO_NEW_DISH, new GoToNewDishPageCommand());
        repository.put(CommandName.GO_TO_USER_ORDERS, new GoToUserOrdersPageCommand());
        repository.put(CommandName.GO_TO_USERS, new GoToUsersPageCommand());
        repository.put(CommandName.GO_TO_MENU, new GoToMenuCommand());
        repository.put(CommandName.DEL_FROM_ORDER, new DelFromOrderCommand());
        repository.put(CommandName.DEL_FROM_MENU, new DelDishFromMenuCommand());
        repository.put(CommandName.GET_ORDER, new GetOrderCommand());
        repository.put(CommandName.CONFIRM_ORDER, new ConfirmOrderCommand());
        repository.put(CommandName.SAVE_ORDER, new SaveOrderCommand());
        repository.put(CommandName.CONFIRM_INVOICE, new ConfirmInvoiceCommand());
    }
    Command getCommand(String name){
        CommandName commandName;
        Command command;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
//write log
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }

}
