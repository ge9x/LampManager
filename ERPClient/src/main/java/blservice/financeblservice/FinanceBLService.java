package blservice.financeblservice;

import util.ResultMessage;
import vo.AccountBillVO;
import vo.AccountVO;
import vo.CustomerVO;

import java.util.ArrayList;

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
     * 获得所有客户列表
     * @return 客户VO的列表
     */
    public ArrayList<CustomerVO> getAllCustomer();

    /**
     * 获得所有银行账户列表
     * @return
     */
    public ArrayList<AccountVO> getAllAccount();

    /**
     * 提交单据
     * @return 是否成功
     */
    public ResultMessage submit(AccountBillVO vo);

    /**
     * 保存为草稿单据
     * @return
     */
    public ResultMessage save(AccountBillVO vo);

    /**
     * 修改草稿单据
     * @param vo
     * @return
     */
    public ResultMessage updateDraft(AccountBillVO vo);

}
