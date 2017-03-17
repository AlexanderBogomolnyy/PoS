package pos.model.entity;

import pos.model.PoS;
import pos.model.Coin;
import pos.model.Product;
import pos.model.entity.sale.MemorySaleArchive;
import pos.model.entity.sale.Sale;
import pos.model.entity.sale.SaleArchive;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * /**
 * The beverage PoS class.
 * This class implements {@link PoS} interface and represents behavior of
 * PoS for sale beverages.
 */
public class BeveragePoS implements PoS<Coin> {

    /**
     * A constant holds the empty product.
     * This is the auxiliary kind of product with empty inner fields.
     */
    private static final Product NO_PRODUCT = Beverage.NO_BEVERAGE;

    /**
     * A field contains coin terminal
     */
    private CoinTerminal terminal = new CoinTerminal();

    /**
     * A field contains map of products
     */
    private Map<Product, Integer> productStock = new HashMap<>();

    /**
     * A field contains archive of PoS sales
     */
    private SaleArchive archive = new MemorySaleArchive();

    /**
     * The constructor with map of products.
     *
     * @param productStock map of products
     */
    public BeveragePoS(Map<Product, Integer> productStock) {
        this.productStock = productStock;
    }

    /**
     * See {@see PoS#addMoney(int)}
     *
     * @param coin - coin for adding
     * @return true if money added
     *         false in opposite case
     */
    @Override
    public boolean addMoney(Coin coin) {
        return terminal.insertCoin(coin);
    }

    /**
     * See {@see PoS#saleProduct(int)}
     *
     * @param id - product ID
     * @return product for current ID if all conditions for sale is occupied
     *         or empty product if product with such ID can't be sale
     */
    @Override
    public Product saleProduct(int id) {
        Product product = productStock.keySet()
                .stream()
                .map(p -> p)
                .filter(p -> p.getId() == id)
                .findAny()
                .orElse(NO_PRODUCT);
        if(isPurchaseAvailable(product)) {
            productStock.put(product, productStock.get(product) - 1);
            terminal.debitFromAccount(product.getCost());
            archive.saveSale(new Sale(LocalDateTime.now(),product, product.getCost()));
            return product;
        }
        return NO_PRODUCT;
    }

    /**
     * See {@see PoS#finishSales()}
     *
     * @return list of refunded coins
     */
    @Override
    public List<Coin> finishSales() {
        return terminal.getRefund();
    }

    /**
     * See {@see PoS#accountBalance()}
     *
     * @return account balance
     */
    @Override
    public int accountBalance() {
        return terminal.getAccount();
    }

    /**
     * See {@see PoS#getAvailableProductSet()}
     *
     * @return set of products
     */
    @Override
    public Set<Product> getAvailableProductSet() {
        return productStock.entrySet()
                .stream()
                .filter(en -> en.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * This method sets the map of products
     *
     * @param productStock map of product
     */
    public void setProductStock(Map<Product, Integer> productStock) {
        this.productStock = productStock;
    }

    /**
     * This method adds one product to the map of product for sale
     *
     * @param product - product type
     * @param quantity - quantity of product
     */
    public void addProductToStock(Product product, int quantity) {
        productStock.put(product, quantity);
    }

    // test if PoS can sale the product
    private boolean isPurchaseAvailable(Product product) {
        return productStock.get(product) != null
                && productStock.get(product) > 0
                && terminal.isEnoughMoneyOnAccount(product.getCost());
    }

}
