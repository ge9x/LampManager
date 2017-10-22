package po;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;


/**
 * created by zlk on 2017/10/20
 */

public class SalesPO {
	
	/**单据类型*/
	private BillType type;
	/**单据状态*/
	private BillState state;
	/**单据编号*/
	private String ID;
	/**客户*/
	private String customer;
	/**业务员*/
	private String salesman;
	/**操作员*/
	private String user;
	/**仓库*/
	private String inventory;
	/**商品列表*/
	private GoodsItemPO goodsItem;
	/**折让前总额*/
	private double beforeSum;
	/**折让*/
	private double allowance;
	/**使用代金券金额*/
	private double voucher;
	/**折让后总额*/
	private double afterSum;
	/**备注*/
	private String remarks;
	/**单据最后修改时间*/
	private Date date;
	
	
	public SalesPO( BillType type, BillState state, String iD, String customer, String salesman,
			String user, String inventory,GoodsItemPO goodsItem, double beforeSum,
			double allowance, double voucher, double afterSum, String remarks, Date endDate) {
		super();
		this.type = type;
		this.state = state;
		ID = iD;
		this.customer = customer;
		this.salesman = salesman;
		this.user = user;
		this.inventory = inventory;
		this.goodsItem = goodsItem;
		this.beforeSum = beforeSum;
		this.allowance = allowance;
		this.voucher = voucher;
		this.afterSum = afterSum;
		this.remarks = remarks;
		this.date = endDate;
	}



	public BillType getType() {
		return type;
	}


	public void setType(BillType type) {
		this.type = type;
	}


	public BillState getState() {
		return state;
	}


	public void setState(BillState state) {
		this.state = state;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getSalesman() {
		return salesman;
	}


	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getInventory() {
		return inventory;
	}


	public void setInventory(String inventory) {
		this.inventory = inventory;
	}


	public GoodsItemPO getGoodsItem() {
		return goodsItem;
	}


	public void setGoodsItem(GoodsItemPO goodsItem) {
		this.goodsItem = goodsItem;
	}


	public double getBeforeSum() {
		return beforeSum;
	}


	public void setBeforeSum(double beforeSum) {
		this.beforeSum = beforeSum;
	}


	public double getAllowance() {
		return allowance;
	}


	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}


	public double getVoucher() {
		return voucher;
	}


	public void setVoucher(double voucher) {
		this.voucher = voucher;
	}


	public double getAfterSum() {
		return afterSum;
	}


	public void setAfterSum(double afterSum) {
		this.afterSum = afterSum;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date endDate) {
		this.date = endDate;
	}
	
	
	
}
