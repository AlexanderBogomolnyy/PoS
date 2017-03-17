package pos.model.entity;

import pos.model.Product;

/**
 * The Beverage class.
 * This class implements {@link Product} interface and represents some kind beverage.
 */
public class Beverage implements Product {

    /**
     * A constant holds the empty beverage.
     * This is the auxiliary kind of beverage without id, cost and description.
     */
    public static final Beverage NO_BEVERAGE = new Beverage(0, 0, "");

    /**
     * The field contains beverage id
     */
    private int id;

    /**
     * The field contains beverage cost
     */
    private int cost;

    /**
     * The field contains beverage description
     */
    private String description;

    /**
     * The constructor with three parameters.
     *
     * @param id - beverage id
     * @param cost beverage cost
     * @param description beverage description
     */
    public Beverage(int id, int cost, String description) {
        this.id = id;
        this.cost = cost;
        this.description = description;
    }

    /**
     * See {@see Product#getId()}
     *
     * @return {@link int} the numerical product identifier
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * See {@see Product#getCost()}
     *
     * @return {@link int} product cost in coins
     */
    @Override
    public int getCost() {
        return cost;
    }

    /**
     * See {@see Product#getDescription()}
     *
     * @return {@link String} product description
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "id=" + id +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }

}
