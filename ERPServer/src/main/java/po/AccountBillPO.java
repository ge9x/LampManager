package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class AccountBillPO extends BillPO {
	 /**
     * 单据最后修改时间
     */
    private String date;

    /**
     * 单据编号
     */
    private int ID;

    /**
     * 单据状态
     */
    private BillState state;

    /**
     * 单据类型
     */
    private BillType type;
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

    public AccountBillPO(int ID, String date, BillType type, BillState state, int customerID, String userName, ArrayList<AccountBillItemPO> accountBillItemPOS) {
        super(ID, date, type, state);
        this.date = date;
        this.ID = ID;
        this.state = state;
        this.type = type;
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItemPOS = accountBillItemPOS;
        this.sum = calSum();
    }
    
    public String getDate() {
		return date;
	}
    
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public BillState getState() {
		return state;
	}
	
	public void setState(BillState state) {
		this.state = state;
	}
	
	public BillType getType() {
		return type;
	}
	
	public void setType(BillType type) {
		this.type = type;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	private double calSum(){
        double sum = 0;
        for (int i = 0; i < accountBillItemPOS.size(); i++){
            sum += accountBillItemPOS.get(i).getMoney();
        }
        return sum;
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
