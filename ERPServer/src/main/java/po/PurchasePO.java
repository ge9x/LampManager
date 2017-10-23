package po;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchasePO {
	/**单据类型*/
	private BillType type;
	/**单据状态*/
	private BillState state;
	/**单据编号*/
	private String ID;
	/**供应商*/
	private String supplier;
	/**仓库*/
	private String inventory;
	/**操作员*/
	private String user;
	/**商品列表*/
	private GoodsItemPO goodsItem;
	/**备注*/
	private String remarks;
	/**总额合计*/
	private double sum;
	/**单据最后修改时间*/
	private Date date;
	
	public PurchasePO(BillType type,BillState state,String ID,String supplier
			,String inventory,String user,GoodsItemPO goodsItem,String remarks
			,double sum,Date endDate){
		this.type=type;
		this.state=state;
		this.ID=ID;
		this.supplier=supplier;
		this.inventory=inventory;
		this.user=user;
		this.goodsItem=goodsItem;
		this.sum=sum;
		this.remarks=remarks;
		this.date=endDate;
	}
/**
	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
	}
*/
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public GoodsItemPO getGoodsItem() {
		return goodsItem;
	}

	public void setGoodsItem(GoodsItemPO goodsItem) {
		this.goodsItem = goodsItem;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date endDate) {
		this.date = endDate;
	}
	
}
