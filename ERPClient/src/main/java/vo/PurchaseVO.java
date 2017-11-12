package vo;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchaseVO extends BillVO{
	/**供应商*/
	public String supplier;
	/**供应商ID*/
	public String customerID;
	/**仓库*/
	public String inventory;
	/**操作员*/
	public String user;
	/**商品列表*/
	public ArrayList<GoodsItemVO> goodsItemList;
	/**备注*/
	public String remarks;
	/**总额合计*/
	public double sum;
	
	public PurchaseVO(BillType type,BillState state,String ID,String supplier
			,String customerID,String inventory,String user,ArrayList<GoodsItemVO> goodsItemList,String remarks
			,Date endDate){
		this.type=type;
		this.state=state;
		this.ID=ID;
		this.supplier=supplier;
		this.customerID=customerID;
		this.inventory=inventory;
		this.user=user;
		this.goodsItemList=goodsItemList;
		this.sum=calSum();
		this.remarks=remarks;
		this.date=endDate;
	}
	
	private double calSum(){
		double sum=0;
		for(int i=0;i<goodsItemList.size();i++){
			sum+=goodsItemList.get(i).sum;
		}
		return sum;
	}
}
