package pos.controller.commands;

import pos.controller.MenuStatus;
import pos.model.Coin;
import pos.model.PoS;
import pos.model.Product;
import pos.model.entity.Beverage;
import pos.view.Messages;
import pos.view.View;

import java.util.*;

/**
 * The Get Beverage Command class
 * This class extends {@link AbstractCommand} class and provides behaviour
 * of getting beverage from the PoS terminal.
 */
public class GetBeverageCommand extends AbstractCommand {

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    public GetBeverageCommand(View view) {
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
        Set<Product> beverages = new TreeSet<>((o1, o2) -> o1.getId() - o2.getId());
        beverages.addAll(pos.getAvailableProductSet());
        showAccountBalance(pos);
        showMenu(beverages);
        do {
            view.printLineMessage(Messages.SELECTING_BEVERAGE_TYPE);
            String result = scanner.nextLine();
            if (Messages.END_COMMAND.equalsIgnoreCase(result))
                break;
            if (Messages.EXIT_COMMAND.equalsIgnoreCase(result))
                return MenuStatus.END;
            int id = parseInt(result);
            if (checkChoice(beverages, id)) {
                Product purchase = pos.saleProduct(id);
                if (!Beverage.NO_BEVERAGE.equals(purchase)) {
                    view.printMessage(Messages.GREETING_BEVERAGE);
                    view.printLineMessage(purchase.toString());
                    break;
                } else {
                    view.printMessage(Messages.ERROR_BUY_BEVERAGE);
                    showAccountBalance(pos);
                }
            } else
                view.printLineMessage(Messages.INPUT_WARNING);
        } while (true);
        return MenuStatus.CONTINUE;
    }

    // shows account balance
    private void showAccountBalance(PoS<Coin> pos) {
        view.printMessage(Messages.ACCOUNT);
        view.printLineMessage(String.valueOf(pos.accountBalance()));
    }

    //shows sub menu for beverages
    private void showMenu(Set<Product> products) {
        for (Product product : products) {
            view.printMessage(String.valueOf(product.getId()));
            view.printMessage(Messages.DELIMITER_DASH);
            view.printMessage(product.getDescription());
            view.printMessage(Messages.LEFT_BRACKET);
            view.printMessage(String.valueOf(product.getCost()));
            view.printLineMessage(Messages.RIGHT_BRACKET);
        }
    }

    // checks if choice is possible
    private boolean checkChoice(Set<Product> products, int id) {
        return products
                .stream()
                .map(Product::getId)
                .filter(i -> i == id)
                .findAny()
                .isPresent();
    }

}
