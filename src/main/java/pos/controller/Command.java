package pos.controller;

import pos.model.Coin;
import pos.model.PoS;

/**
 * The command interface for controller
 */
public interface Command {
    /**
     * The executive command
     *
     * @param pos - model
     * @return - menu status
     */
    MenuStatus execute(PoS<Coin> pos);
}
