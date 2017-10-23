package vo;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchaseVO extends BillVO{
	/**供应商*/
	public String supplier;
	/**仓库*/
	public String inventory;
	/**操作员*/
	public String user;
	/**商品列表*/
	public GoodsItemVO goodsItem;
	/**备注*/
	public String remarks;
	/**总额合计*/
	public double sum;
	
	public PurchaseVO(BillType type,BillState state,String ID,String supplier
			,String inventory,String user,GoodsItemVO goodsItem,String remarks
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
}
