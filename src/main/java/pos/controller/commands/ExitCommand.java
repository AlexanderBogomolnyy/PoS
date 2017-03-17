package pos.controller.commands;

import pos.controller.MenuStatus;
import pos.model.Coin;
import pos.model.PoS;
import pos.view.View;

/**
 * The Exit Command class
 * This class extends {@link AbstractCommand} class and provides marker
 * for stopping work with PoS terminal.
 */
public class ExitCommand extends AbstractCommand {

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    public ExitCommand(View view) {
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
        return MenuStatus.EXIT;
    }
}
