package po;

import java.sql.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import vo.GoodsItemVO;
import vo.UserPosition;

/**
 * created by zlk on 2017/10/20
 */

public class SalesPO {
	/**单据创建时间*/
	public Date date;
	/**单据类型*/
	public BillType type;
	/**单据状态*/
	public BillState state;
	/**单据编号*/
	public String ID;
	/**客户*/
	public String customer;
	/**业务员*/
	public String salesman;
	/**操作员*/
	public UserPosition user;
	/**仓库*/
	public String inventory;
	/**商品列表*/
	public ArrayList<GoodsItemVO> goodsItem;
	/**折让前总额*/
	public double beforeSum;
	/**折让*/
	public double allowance;
	/**使用代金券金额*/
	public double voucher;
	/**折让后总额*/
	public double afterSum;
	/**备注*/
	public String remarks;
	/**单据最后修改时间*/
	public Date endDate;
	
	
	public SalesPO(Date date, po.BillType type, po.BillState state, String iD, String customer, String salesman,
			po.UserPosition user, String inventory, ArrayList<po.GoodsItemVO> goodsItem, double beforeSum,
			double allowance, double voucher, double afterSum, String remarks, Date endDate) {
		super();
		this.date = date;
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
		this.endDate = endDate;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
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


	public UserPosition getUser() {
		return user;
	}


	public void setUser(UserPosition user) {
		this.user = user;
	}


	public String getInventory() {
		return inventory;
	}


	public void setInventory(String inventory) {
		this.inventory = inventory;
	}


	public ArrayList<GoodsItemVO> getGoodsItem() {
		return goodsItem;
	}


	public void setGoodsItem(ArrayList<GoodsItemVO> goodsItem) {
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


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
