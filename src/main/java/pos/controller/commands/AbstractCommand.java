package pos.controller.commands;

import pos.controller.Command;
import pos.view.View;

/**
 * The Abstract command class.
 * This class implements {@link Command} interface.
 * This class describes abstract command and provide constructor with view parameter.
 */
public abstract class AbstractCommand implements Command {

    /**
     * The field contains view
     */
    protected View view;

    /**
     * The constructor with view parameter
     *
     * @param view - visual interface
     */
    AbstractCommand(View view) {
        this.view = view;
    }

    /**
     * This method parse String to int
     *
     * @param string - {@link String} for parsing
     * @return {@link int} if parse has completed without exceptions,
     *          -1 in other cases
     */
    protected int parseInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

}
