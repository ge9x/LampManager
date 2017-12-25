package blservice.financeblservice;

import bl.financialbl.CashBill;
import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillVO;

import java.io.Reader;
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

    /**
     * 获得所有审批通过的收付款单
     */
    public ArrayList<AccountBillVO> getAccountBillsByDate(String startDate,String endDate);

    /**
     * 获得所有审批通过的现金费用单
     * @param startDate
     * @param endDate
     * @return
     */
    public ArrayList<CashBillVO> getCashBillsByDate(String startDate,String endDate);

    /**
     * 红冲单据：生成一个一模一样但是仅仅把数量取负数的单子并入账，以此来抵消之前的单子。
     * @param billVO
     */
    public ResultMessage redCover(AccountBillVO billVO);
    ResultMessage redCover(CashBillVO billVO);

    /**
     * 红冲并复制单据：在红冲的基础上，新建一张以之前单子为模板的草稿单，给用户以编辑的机会。
     * @param billVO
     */
    ResultMessage redCoverAndCopy(AccountBillVO billVO);
    ResultMessage redCoverAndCopy(CashBillVO billVO);

}
