package pos.controller.commands;

import pos.controller.MenuStatus;
import pos.model.Coin;
import pos.model.PoS;
import pos.view.Messages;
import pos.view.View;

import java.util.List;

/**
 * The Get Change class
 * This class extends {@link AbstractCommand} class and provides behaviour
 * of getting change from the PoS terminal.
 */
public class GetChangeCommand extends AbstractCommand {

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    public GetChangeCommand(View view) {
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
        List<Coin> refund = pos.finishSales();
        if (refund.isEmpty()) {
            view.printMessage(Messages.NO_CHANGE);
        } else {
            view.printMessage(Messages.KEEPING_CHANGE);
            view.printList(refund);
        }
        return MenuStatus.CONTINUE;
    }
}
