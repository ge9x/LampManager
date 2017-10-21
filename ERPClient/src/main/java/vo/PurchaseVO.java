package vo;

import java.sql.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class PurchaseVO {
	/**单据创建时间*/
	public Date startDate;
	/**单据类型*/
	public BillType type;
	/**单据状态*/
	public BillState state;
	/**单据编号*/
	public String ID;
	/**供应商*/
	public String supplier;
	/**仓库*/
	public String inventory;
	/**操作员*/
	public UserPosition user;
	/**商品列表*/
	public ArrayList<GoodsItemVO> goodsItem;
	/**备注*/
	public String remarks;
	/**总额合计*/
	public double sum;
	/**单据最后修改时间*/
	public Date endDate;
	
	public PurchaseVO(Date startDate,BillType type,BillState state,String ID,String supplier
			,String inventory,UserPosition user,ArrayList<GoodsItemVO> goodsItem,String remarks
			,double sum,Date endDate){
		this.startDate=startDate;
		this.type=type;
		this.state=state;
		this.ID=ID;
		this.supplier=supplier;
		this.inventory=inventory;
		this.user=user;
		this.goodsItem=goodsItem;
		this.sum=sum;
		this.remarks=remarks;
		this.endDate=endDate;
	}
}
