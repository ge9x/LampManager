package blservice.financeblservice;

import util.ResultMessage;
import vo.AccountBillVO;

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

    /**
     * 获得当前用户ID
     * @return 当前用户ID
     */
    public String getUserID();


    /**
     * 提交单据
     * @return 是否成功
     */
    public ResultMessage submit();

    /**
     * 保存为草稿单据
     * @return
     */
    public ResultMessage save();

    /**
     * 修改草稿单据
     * @param vo
     * @return
     */
    public ResultMessage updateDraft(AccountBillVO vo);
}
