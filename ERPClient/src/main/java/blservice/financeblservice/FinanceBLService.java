package blservice.financeblservice;

/**
 * Created by Kry·L on 2017/10/21.
 */
public interface FinanceBLService {

    /**
     * 获得新建的收款单单据编号
     * @return 收款单单据编号
     */
    public String getNewReceiptID();

    /**
     * 获得新建的付款单单据编号
     * @return 付款单单据编号
     */
    public String getNewPaymentID();

    /**
     * 获得新建的现金费用单单据编号
     * @return 现金费用单单据编号
     */
    public String getNewCashBillID();

    public AccountBillVO
}
