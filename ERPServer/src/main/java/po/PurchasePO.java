package po;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchasePO extends BillPO{
	/**供应商*/
	private String supplier;
	/**供应商ID*/
	private String customerID;
	/**仓库*/
	private String inventory;
	/**操作员*/
	private String user;
	/**商品列表*/
	private ArrayList<GoodsItemPO> goodsItemList;
	/**备注*/
	private String remarks;
	/**总额合计*/
	private double sum;
	
	
	public PurchasePO(BillType type,BillState state,String ID,String supplier
			,String customerID,String inventory,String user,ArrayList<GoodsItemPO> goodsItemList,String remarks
			,Date endDate){
		super(ID, endDate, type, state);
		this.supplier=supplier;
		this.customerID=customerID;
		this.inventory=inventory;
		this.user=user;
		this.goodsItemList=goodsItemList;
		this.sum=calSum();
		this.remarks=remarks;
	}
	
	private double calSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).getSum();
		}
		return sum;
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

	public ArrayList<GoodsItemPO> getGoodsItemList() {
		return goodsItemList;
	}

	public void setGoodsItemList(ArrayList<GoodsItemPO> goodsItemList) {
		this.goodsItemList = goodsItemList;
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

	
}
