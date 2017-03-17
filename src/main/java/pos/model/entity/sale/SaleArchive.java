package pos.model.entity.sale;

/**
 * The sale Archive interface
 * This interface describes some kind archive for storing sale items which
 * has the following properties:
 * - save sale to the archive;
 * - generate the general report
 */
public interface SaleArchive {
    /**
     * This method adds item of sale to the archive
     *
     * @param sale sale item
     */
    void saveSale(Sale sale);

    /**
     * This method returns text of the report by the archive
     *
     * @return {@link String} contains report
     */
    String generateReport();
}
