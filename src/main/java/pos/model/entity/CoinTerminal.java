package pos.model.entity;

import pos.model.Terminal;
import pos.model.Coin;

import java.util.*;

/**
 * The Coin terminal class.
 * This class implements {@link Terminal} interface and represents behavior of
 * terminal which can be used for getting money in coins. This class isn't
 * tied to a specific currency and can be used for work with some kind of an
 * abstract coins.
 */
public class CoinTerminal implements Terminal {

    /**
     * The array of coin denomination supported by the terminal
     */
    private static final Integer[] SUPPORTED_COIN_DENOMINATION = {1, 5, 10, 25, 50};

    /**
     * The balance on temporary account.
     * This account is intended for keeping current balance as long as part of money on it won't be
     * moved to the main total account. After moving, money can't be received for refunding.
     */
    private int account;

    /**
     * The balance of main total account.
     * This account contains the total balance of all performed operation.
     */
    private int totalAccount;

    /**
     * This map contains all coins caught in terminal
     */
    private Map<Coin, Integer> coinWarehouse = new HashMap<>();

    /**
     * This list contains all unsupported coins which have caught in terminal
     */
    private List<Coin> unsupportedCoins = new ArrayList<>();

    /**
     * See {@see Terminal#isEnoughMoneyOnAccount(int)}
     *
     * @param money - money value
     * @return true if debiting possible
     *         false in opposite case
     */
    @Override
    public boolean isEnoughMoneyOnAccount(int money) {
        return (account - money) >= 0;
    }

    /**
     * See {@see Terminal#debitFromAccount(int))
     *
     * @param money - money value
     * @return true if money has debited
     *         false in opposite case (for example, the amount of money on account
     *                                 less then requested amount)
     */
    @Override
    public boolean debitFromAccount(int money) {
        if (isEnoughMoneyOnAccount(money)) {
            totalAccount += money;
            account -= money;
            return true;
        }
        return false;
    }

    /**
     * See {@see Terminal#getAccount())
     *
     * @return the balance of account
     */
    @Override
    public int getAccount() {
        return account;
    }

    /**
     * This method inserts coins into the terminal
     *
     * @param coin - {@link Coin} inserted in terminal
     * @return true if coin has been inserted
     *         false in opposite case
     */
    public boolean insertCoin(Coin coin) {
        if (checkSupportedCoin(coin)) {
            storeCoinInWarehouse(coinWarehouse, coin);
            addMoneyToAccount(coin.getValue());
            return true;
        } else {
            unsupportedCoins.add(coin);
            return false;
        }
    }

    /**
     * This method returns refund from balance of account as {@link List<Coin>}.
     * This method returns empty List if account balance has no refund.
     * All coins which unsupported by the terminal fall into refund coin list.
     *
     * @return {@link List<Coin>} which makes up the refund from balance of account
     */
    public List<Coin> getRefund(){
        List<Coin> refund = new ArrayList<>(unsupportedCoins);
        unsupportedCoins.clear();
        List<Coin> accountRefund = prepareRefund(account);
        int moneyOnAccount = accountRefund.stream().mapToInt(Coin::getValue).sum();
        account -= moneyOnAccount;
        refund.addAll(accountRefund);
        return refund;
    }

    // prepare list of coin for current amount of money
    private List<Coin> prepareRefund(int money) {
        List<Coin> refund = new ArrayList<>();
        Set<Coin> sortedCoinWarehouseKeys = new TreeSet<>((o1, o2) -> o2.getValue() - o1.getValue());
        sortedCoinWarehouseKeys.addAll(coinWarehouse.keySet());
        for (Coin coin: sortedCoinWarehouseKeys) {
            int refunded = getAvailableRefundedByCoinDenomination(coin, money);
            for(int i = 0; i < refunded; i++) {
                refund.add(coin);
                removeCoinFromWarehouse(coinWarehouse, coin);
            }
            money -= refunded*coin.getValue();
        }
        return refund;
    }

    // return minimum value between needed coins with current denomination
    // or contained coins in warehouse for current money value
    private int getAvailableRefundedByCoinDenomination(Coin coin, int money) {
        int expected = money/coin.getValue();
        int available = coinWarehouse.get(coin);
        return Math.min(expected, available);
    }

    // checks if current coin supports by the terminal
    private boolean checkSupportedCoin(Coin coin) {
        return Arrays
                .asList(SUPPORTED_COIN_DENOMINATION)
                .contains(coin.getValue());
    }

    // adds money to temporary account
    private void addMoneyToAccount(int money) {
        account += money;
    }

    // stores coin in warehouse
    private void storeCoinInWarehouse(Map<Coin, Integer> warehouse, Coin coin) {
        if (warehouse.containsKey(coin))
            warehouse.put(coin, warehouse.get(coin) + 1);
        else
            warehouse.put(coin, 1);
    }

    // removes coin from warehouse
    private void removeCoinFromWarehouse(Map<Coin, Integer> warehouse, Coin coin) {
        if (warehouse.keySet().contains(coin)) {
            warehouse.put(coin, warehouse.get(coin) - 1);
        }
    }

}
