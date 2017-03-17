package pos.controller.commands;

import pos.controller.MenuStatus;
import pos.model.Coin;
import pos.model.PoS;
import pos.model.entity.SimpleCoin;
import pos.view.Messages;
import pos.view.View;

import java.util.Scanner;

/**
 * The Add Money Command class
 * This class extends {@link AbstractCommand} class and provides behaviour
 * of adding money to the PoS terminal.
 */
public class AddMoneyCommand extends AbstractCommand {

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    public AddMoneyCommand(View view) {
        super(view);
    }

    /**
     * See {@see Command#execute(Pos)}
     *
     * @param pos - model
     * @return - status after execution
     */
    @Override
    public MenuStatus execute(PoS<Coin> pos) {
        Scanner scanner = new Scanner(System.in);
        do {
            view.printMessage(Messages.ENTERING_COIN_DENOMINATION);
            String result = scanner.nextLine();
            if (Messages.END_COMMAND.equalsIgnoreCase(result))
                break;
            if (Messages.EXIT_COMMAND.equalsIgnoreCase(result))
                return MenuStatus.END;
            int coinDenomination = getCoinDenomination(result);
            insertCoin(pos, new SimpleCoin(result, coinDenomination));
        } while (true);
        return MenuStatus.CONTINUE;
    }

    // return coin denomination in integer
    private int getCoinDenomination(String result) {
        int coinDenomination = parseInt(result);
        if (coinDenomination <= 0) {
            view.printLineMessage(Messages.INPUT_WARNING);
        }
        return coinDenomination;
    }

    // inserts coin to the PoS terminal
    private void insertCoin(PoS<Coin> pos, Coin coin) {
        boolean status = pos.addMoney(coin);
        if (status) {
            view.printLineMessage(Messages.COIN_ADDED);
            view.printMessage(Messages.ACCOUNT);
            view.printLineMessage(String.valueOf(pos.accountBalance()));
        } else
            view.printLineMessage(Messages.UNSUPPORTED_COIN_DENOMINATION);
    }

}
