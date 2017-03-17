package pos.model;

/**
 * The terminal interface.
 * This interface describes behaviour of cash terminal.
 * This terminal have to control the amount of money on its account,
 * get information about value of the money on account and debit money
 * from the account if it is possible.
 */
public interface Terminal {

    /**
     * This method return information about possibility of debiting specific
     * value of money from account
     *
     * @param money - money value
     * @return true if debiting possible
     *         false in opposite case
     */
    boolean isEnoughMoneyOnAccount(int money);

    /**
     * This method debit money from account
     *
     * @param money - money value
     * @return true if money has debited
     *         false in opposite case (for example, the amount of money on account
     *                                 less then requested amount)
     */
    boolean debitFromAccount(int money);

    /**
     * This method returns the amount of money on account
     *
     * @return the balance of account
     */
    int getAccount();

}
