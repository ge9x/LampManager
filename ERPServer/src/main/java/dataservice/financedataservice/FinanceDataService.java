package dataservice.financedataservice;

import com.sun.xml.internal.bind.v2.model.core.ID;
import po.BillPO;
import util.ResultMessage;

import java.lang.reflect.Array;

/**
 * Created by Kry·L on 2017/10/22.
 */
public interface FinanceDataService {

    /**
     * 获得新的收款单ID
     * @return
     */
    public String getNewReceiptID();

    /**
     * 获得新的付款单ID
     * @return
     */
    public String getNewPaymentID();

    /**
     * 获得新的现金费用单
     * @return
     */
    public String getNewCashBillID();

    public ResultMessage addBill(BillPO po);

}
