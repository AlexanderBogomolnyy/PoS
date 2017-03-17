package pos.model.entity.sale;

import java.util.ArrayList;
import java.util.List;

/**
 * The Memory sale archive class
 * This class implements {@link SaleArchive} interface and represents
 * simple archive of sale in compute memory.
 * This archive alive as long as it keeps in device memory
 */
public class MemorySaleArchive implements SaleArchive {

    /**
     * This field contains the list of sales
     */
    private List<Sale> saleList = new ArrayList<>();

    /**
     * See {@see SaleArchive#saveSale(Sale)}
     *
     * @param sale sale item
     */
    @Override public void saveSale(Sale sale) {
        saleList.add(sale);
    }

    /**
     * See {@see SaleArchive#generateReport()}
     *
     * @return {@link String} contains report
     */
    @Override public String generateReport() {
        String result = "";
        for (Sale sale: saleList) {
            result += sale.toString();
        }
        return result;
    }

}
