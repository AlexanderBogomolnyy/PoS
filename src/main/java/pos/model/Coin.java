package pos.model;

/**
 * The coin interface
 * This interface describes some kind of abstract coin which has the following
 * properties:
 * - identifier (currency type, for example);
 * - value (coin denomination)
 */
public interface Coin {

    /**
     * This method returns currency type for the coin
     * @return currency type
     */
    String getIdentifier();

    /**
     * This method returns coin denomination
     *
     * @return coin denomination
     */
    int getValue();

}
