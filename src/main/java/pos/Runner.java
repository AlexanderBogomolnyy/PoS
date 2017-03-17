package pos;

import pos.controller.Controller;
import pos.model.entity.Beverage;
import pos.model.entity.BeveragePoS;
import pos.model.Product;
import pos.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the main starting class
 */
public class Runner {

    public static void main(String[] args) {
        //initialization of map of products and its quantities,
        //which can by solved by the PoS terminal
        Map<Product, Integer> productStock = new HashMap<>();
        productStock.put(new Beverage(1, 25, "Tea"), 10);
        productStock.put(new Beverage(2, 35, "Coffee"), 10);
        productStock.put(new Beverage(3, 45, "Juice"), 10);
        productStock.put(new Beverage(4, 75, "Beer"), 1);

        Controller controller = new Controller(new BeveragePoS(productStock), new View());
        controller.start();

    }
}
