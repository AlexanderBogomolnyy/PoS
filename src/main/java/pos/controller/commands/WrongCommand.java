package pos.controller.commands;

import pos.controller.MenuStatus;
import pos.model.Coin;
import pos.model.PoS;
import pos.view.Messages;
import pos.view.View;

/**
 * The Wrong Command class
 * This class extends {@link AbstractCommand} class and provides marker
 * for wrong(unsupported) operation with PoS terminal.
 */
public class WrongCommand extends AbstractCommand {

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    public WrongCommand(View view) {
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
        view.printLineMessage(Messages.INPUT_WARNING);
        return MenuStatus.CONTINUE;
    }

}
