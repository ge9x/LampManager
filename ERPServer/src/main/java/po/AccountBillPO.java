package po;

import util.BillState;
import util.BillType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "accountbill")
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
    private List<AccountBillItemPO> accountBillItemPOS;

    /**
     * 总额汇总
     */
    private double sum;

    public AccountBillPO(){ }

    public AccountBillPO(String date, BillType type, BillState state, int customerID, String userName, ArrayList<AccountBillItemPO> accountBillItemPOS) {
        super(date, type, state);
        this.date = date;
        this.state = state;
        this.type = type;
        this.customerID = customerID;
        this.userName = userName;
        this.accountBillItemPOS = accountBillItemPOS;
        this.sum = calSum();
    }

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
    @Deprecated
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
    
    @Column(name = "date")
    public String getDate() {
		return date;
	}
    
	public void setDate(String date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}

    @Column(name = "state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return state;
	}
	
	public void setState(BillState state) {
		this.state = state;
	}

    @Column(name = "type")
	@Enumerated(EnumType.STRING)
	public BillType getType() {
		return type;
	}
	
	public void setType(BillType type) {
		this.type = type;
	}

    @Column(name = "customerid")
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

    @Column(name = "name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "itemid")
    public List<AccountBillItemPO> getAccountBillItemPOS() {
        return accountBillItemPOS;
    }

    public void setAccountBillItemPOS(List<AccountBillItemPO> accountBillItemPOS) {
        this.accountBillItemPOS = accountBillItemPOS;
    }

    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
