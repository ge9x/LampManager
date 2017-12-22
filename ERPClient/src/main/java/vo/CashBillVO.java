package vo;

import util.BillState;
import util.BillType;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillVO extends BillVO{

    /**
     * 操作员（当前登录用户）名称
     */
    public String userName;

    /**
     * 银行账户名称
     */
    public String accountID;

    /**
     * 条目清单
     */
    public ArrayList<CashBillItemVO> cashBillItems;

    /**
     * 总额
     */
    public double sum;

    public CashBillVO(String date, String ID, BillState state, BillType type, String userName, String accountID, ArrayList<CashBillItemVO> cashBillItems, double sum) {
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItems = cashBillItems;
        this.sum = sum;
    }
}
