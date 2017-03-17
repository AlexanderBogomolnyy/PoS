package pos.model;

import java.util.List;
import java.util.Set;

/**
 * The Point of Sale terminal interface.
 * This interface describes behaviour of abstract point of sale.
 * This PoS provides methods of adding money, sale product, finishing sale
 * with getting refund, checking PoS temporary account balance, getting set
 * of available products for sale.
 *
 * @param <E> - currency type
 */
public interface PoS<E> {

    /**
     * This method adds the money to the PoS
     *
     * @param money - money instance
     * @return true if money added
     *         false in opposite case
     */
    boolean addMoney(E money);


    /**
     * This method specifies behavior of product sale
     *
     * @param id - product ID
     * @return product for current ID if all conditions for sale is occupied
     *         or empty product if product with such ID can't be sale
     */
    Product saleProduct(int id);

    /**
     * This method returns the refund after sales is finished
     *
     * @return list of refunded money items
     */
    List<E> finishSales();

    /**
     * This method returns account balance
     *
     * @return account balance
     */
    int accountBalance();

    /**
     * This method returns the set of available products for sale
     *
     * @return set of products
     */
    Set<Product> getAvailableProductSet();
}
