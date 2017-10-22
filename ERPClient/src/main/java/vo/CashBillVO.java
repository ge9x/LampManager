package vo;

import util.BillState;
import util.BillType;

import java.util.ArrayList;
import java.util.Date;

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
    public String accountName;

    /**
     * 条目清单
     */
    public ArrayList<CashBillItemVO> cashBillItems;

    /**
     * 总额
     */
    public double sum;

    public CashBillVO(Date date, String ID, BillState state, BillType type, String userName, String accountName, ArrayList<CashBillItemVO> cashBillItems, double sum) {
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
        this.userName = userName;
        this.accountName = accountName;
        this.cashBillItems = cashBillItems;
        this.sum = sum;
    }
}
