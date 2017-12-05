package blservice.financeblservice;

import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.CashBillVO;

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
     * @param vo
     * @return
     */
    public ResultMessage examine(CashBillVO vo);
}
