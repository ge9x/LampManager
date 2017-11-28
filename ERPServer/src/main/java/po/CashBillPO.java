package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class CashBillPO extends BillPO {

    /**
     * 操作员
     */
    private String userName;

    /**
     * 银行账户
     */
    private String accountID;

    /**
     * 条目清单
     */
    private ArrayList<CashBillItemPO> cashBillItemPOS;

    /**
     * 总额
     */
    private double sum;

    public CashBillPO(String ID, Date date, BillType type, BillState state, String userName, String accountID, ArrayList<CashBillItemPO> cashBillItemPOS, double sum) {
        super(ID, date, type, state);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        this.sum = sum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountID;
    }

    public void setAccountName(String accountName) {
        this.accountID = accountName;
    }

    public ArrayList<CashBillItemPO> getCashBillItemPOS() {
        return cashBillItemPOS;
    }

    public void setCashBillItemPOS(ArrayList<CashBillItemPO> cashBillItemPOS) {
        this.cashBillItemPOS = cashBillItemPOS;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
