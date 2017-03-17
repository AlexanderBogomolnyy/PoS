package pos.controller;

import pos.controller.commands.*;
import pos.model.Coin;
import pos.model.PoS;
import pos.view.Messages;
import pos.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The main controller for the application
 */
public class Controller {

    private Map<String, Command> commands;
    private Command wrongCommand;
    private Command exitCommand;

    /**
     * Point of sale model
     */
    private PoS<Coin> pos;
    /**
     * Visual interface
     */
    private View view;

    /**
     * The constructor for controller with model and view
     *
     * @param pos - point of sale model
     * @param view - visual interface
     */
    public Controller(PoS<Coin> pos, View view) {
        this.pos = pos;
        this.view = view;
        this.commands = initCommand(view);
        this.wrongCommand = new WrongCommand(view);
        this.exitCommand = new ExitCommand(view);
    }

    // initializing of main menu
    private Map<String, Command> initCommand(View view) {
        return new HashMap<String, Command>() {{
            put("1", new AddMoneyCommand(view));
            put("2", new GetBeverageCommand(view));
            put("3", new GetChangeCommand(view));
            put("0", new EndCommand(view));
        }};
    }

    /**
     * This is the main controller executive method.
     */
    public void start() {
        view.printLineMessage(Messages.GREETING);
        Scanner scanner = new Scanner(System.in);
        MenuStatus response;
        do {
            view.printMessage(Messages.ACCOUNT);
            view.printLineMessage(String.valueOf(pos.accountBalance()));
            view.printLineArray(Messages.MAIN_MENU);
            view.printMessage(Messages.CHOICE);
            String result = scanner.nextLine();
            response = commands.getOrDefault(result, wrongCommand).execute(pos);
            if(response == MenuStatus.END) {
                commands.get("3").execute(pos);
                response = exitCommand.execute(pos);
            }
        } while (response != MenuStatus.EXIT);
    }

}
