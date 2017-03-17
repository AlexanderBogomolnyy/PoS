package pos.view;

/**
 * The interface contains constants with messages for console UI
 */
public interface Messages {

    String GREETING = "-= Beverage POS terminal =-";
    String END_COMMAND = "END";
    String EXIT_COMMAND = "EXIT";
    String INPUT_WARNING = "Incorrect input!";
    String CHOICE = "Your choice: ";

    String[] MAIN_MENU = {
            "1 - Replenish the balance",
            "2 - Get a beverage",
            "3 - Get the change",
            "- - - - - - - - - - - - - - -",
            "0 - Exit "
    };

    String UNSUPPORTED_COIN_DENOMINATION = "This coin is unsupported in the PoS!";
    String COIN_ADDED = "Coin successfully added!";
    String ENTERING_COIN_DENOMINATION = "Enter the coin denomination [type \"" + END_COMMAND +
            "\" - for finish, \"" + EXIT_COMMAND + "\" - for exit]: ";

    String GREETING_BEVERAGE = "Take your beverage: ";
    String ERROR_BUY_BEVERAGE = "[WARNING] Beverage is ended or account balance is not enough! ";
    String SELECTING_BEVERAGE_TYPE = "Select the beverage [type \"" + END_COMMAND +
            "\" - for finish, \"" + EXIT_COMMAND + "\" - for exit]: ";

    String ACCOUNT = "Account balance: ";
    String KEEPING_CHANGE = "Keep the change, please: ";
    String NO_CHANGE = "Your account is empty!";

    String DELIMITER_DASH = " - ";
    String LEFT_BRACKET = " (";
    String RIGHT_BRACKET = ") ";

}
