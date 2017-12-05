package blservice.financeblservice;

import bl.financialbl.CashBill;
import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/12/5.
 */
public interface FinanceInfo {
    /**
     * 审批收付款单
     * 可以修改信息
     * @param vo
     * @return
     */
    public ResultMessage examine(AccountBillVO vo);

    /**
     * 审批现金费用单
     * 可以修改信息
     *
     * @param vo
     * @return
     */
    public ResultMessage examine(CashBillVO vo);

    /**
     * 获得所有待审批收款单
     * @return
     */
    public ArrayList<AccountBillVO> getAllSubmittedReceipts();

    /**
     * 获得所有付款单
     * @return
     */
    public ArrayList<AccountBillVO> getAllSubmittedPayments();

    /**
     * 获得所有待审批现金费用单
     * @return
     */
    public ArrayList<CashBillVO> getAllSubmittedCashBills();
}
