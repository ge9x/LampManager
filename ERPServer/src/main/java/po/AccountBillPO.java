package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class AccountBillPO extends BillPO {
    /**
     * 客户的ID
     */
    private int customerID;

    /**
     * 操作员
     */
    private String userName;

    /**
     * 转账列表
     */
    private ArrayList<AccountBillItemPO> accountBillItemPOS;

    /**
     * 总额汇总
     */
    private double sum;

    public AccountBillPO(String ID, Date date, BillType type, BillState state, int customerID, String userName, ArrayList<AccountBillItemPO> accountBillItemPOS) {
        super(ID, date, type, state);
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItemPOS = accountBillItemPOS;
        this.sum = calSum();
    }
    private double calSum(){
        double sum = 0;
        for (int i = 0; i < accountBillItemPOS.size(); i++){
            sum += accountBillItemPOS.get(i).getMoney();
        }
        return sum;
    }

    public int getCustomerName() {
        return customerID;
    }

    public void setCustomerName(int customerID) {
        this.customerID = customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<AccountBillItemPO> getAccountBillItemPOS() {
        return accountBillItemPOS;
    }

    public void setAccountBillItemPOS(ArrayList<AccountBillItemPO> accountBillItemPOS) {
        this.accountBillItemPOS = accountBillItemPOS;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
