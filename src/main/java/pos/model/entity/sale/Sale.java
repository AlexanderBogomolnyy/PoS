package pos.model.entity.sale;

import pos.model.Product;

import java.time.LocalDateTime;

/**
 * The class describes sale.
 */
public class Sale {

    /**
     * The field contains time of sale
     */
    private LocalDateTime time;

    /**
     * The field contains sale product
     */
    private Product product;

    /**
     * The field contains product worst
     */
    private int worth;

    /**
     * The constructor with three parameters.
     *
     * @param time - time of sale
     * @param product - sale product
     * @param worth - product worth
     */
    public Sale(LocalDateTime time, Product product, int worth) {
        this.time = time;
        this.product = product;
        this.worth = worth;
    }

    /**
     * This method returns time of sale
     *
     * @return time of sale
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * This method returns the sale product
     *
     * @return sale product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * This method returns the cost of product item
     *
     * @return product worth
     */
    public int getWorth() {
        return worth;
    }

}
