package pos.model.entity;

import pos.model.Coin;

/**
 * The Simple Coin class.
 * This class implements {@link Coin} interface and represents some kind of
 * virtual coin
 */
public class SimpleCoin implements Coin{

    /**
     * Currency type of the coin
     */
    private String identifier;

    /**
     * Coin denomination
     */
    private int value;

    /**
     * The constructor with two parameters.
     *
     * @param identifier - currency type
     * @param value  coin denomination
     */
    public SimpleCoin(String identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    /**
     * See {@see Coin#getIdentifier()}
     *
     * @return currency type
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * See {@see Coin#getValue())
     *
     * @return coin denomination
     */
    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coin{'" + value + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleCoin that = (SimpleCoin) o;
        if (value != that.value) return false;
        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;

    }

    @Override
    public int hashCode() {
        int result = identifier != null ? identifier.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }
}
