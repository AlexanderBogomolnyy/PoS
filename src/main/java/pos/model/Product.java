package pos.model;

/**
 * The product interface
 * This interface describes some kind of product which has the following
 * properties:
 * - id (some kind of product numeric identifier);
 * - description (the general text description of product, {@link String} field);
 * - cost (the product cost in coins of a specific currency, {@link int} value)
 */
public interface Product {

    /**
     * This method returns the numerical product identifier
     *
     * @return {@link int} the numerical product identifier
     */
    int getId();

    /**
     * This method returns product description in text representation
     *
     * @return {@link String} product description
     */
    String getDescription();

    /**
     * This method returns product cost of a specific currency
     *
     * @return {@link int} product cost in coins
     */
    int getCost();

}
