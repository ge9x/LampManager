package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;

import javax.persistence.*;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "cashbill")
public class CashBillPO extends BillPO {
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
     * 操作员
     */
    private String userName;

    /**
     * 银行账户
     */
    private int accountID;

    /**
     * 条目清单
     */
    private ArrayList<CashBillItemPO> cashBillItemPOS;

    /**
     * 总额
     */
    private double sum;
    
    public CashBillPO(){ }
    
    public CashBillPO(String date, BillType type, BillState state, String userName, int accountID, ArrayList<CashBillItemPO> cashBillItemPOS, double sum) {
        super(date, type, state);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        this.sum = sum;
    }

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
    public CashBillPO(int ID, String date, BillType type, BillState state, String userName, int accountID, ArrayList<CashBillItemPO> cashBillItemPOS, double sum) {
        super(ID, date, type, state);
        this.userName = userName;
        this.accountID = accountID;
        this.cashBillItemPOS = cashBillItemPOS;
        this.sum = sum;
    }

	@Column(name = "username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	@Column(name = "accountid")
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
}
