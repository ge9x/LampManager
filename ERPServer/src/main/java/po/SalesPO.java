package po;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;


/**
 * created by zlk on 2017/10/20
 */

public class SalesPO extends BillPO{
	
	/**客户*/
	private String customer;
	/**客户ID*/
	private String customerID;
	/**业务员*/
	private String salesman;
	/**操作员*/
	private String user;
	/**仓库*/
	private String inventory;
	/**商品列表*/
	private ArrayList<GoodsItemPO> goodsItemList;
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
	
	
	public SalesPO( BillType type, BillState state, String ID, String customer,String customerID, String salesman,
			String user, String inventory,ArrayList<GoodsItemPO> goodsItemList,
			double allowance, double voucher, String remarks, Date endDate) {
		super(ID, endDate, type, state);
		this.customer = customer;
		this.customerID=customerID;
		this.salesman = salesman;
		this.user = user;
		this.inventory = inventory;
		this.goodsItemList = goodsItemList;
		this.beforeSum = calBeforeSum();
		this.allowance = allowance;
		this.voucher = voucher;
		this.afterSum = calAfterSum();
		this.remarks = remarks;
	}
	
	private double calBeforeSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		return sum;
	}
	
	private double calAfterSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		sum=sum-allowance-voucher;
		return sum;
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


	public ArrayList<GoodsItemPO> getGoodsItemList() {
		return goodsItemList;
	}


	public void setGoodsItemList(ArrayList<GoodsItemPO> goodsItemList) {
		this.goodsItemList = goodsItemList;
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

	
}
