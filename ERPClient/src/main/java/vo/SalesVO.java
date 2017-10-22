package vo;

import java.util.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;
import util.UserPosition;

public class SalesVO extends BillVO{
	/**单据创建时间*/
	public Date startDate;
	/**客户*/
	public String customer;
	/**业务员*/
	public String salesman;
	/**操作员*/
	public UserPosition user;
	/**仓库*/
	public String inventory;
	/**商品列表*/
	public GoodsItemVO goodsItem;
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
	
	
	public SalesVO(Date startDate, BillType type, BillState state, String ID, String customer, String salesman,
			UserPosition user, String inventory, GoodsItemVO goodsItem, double beforeSum, double allowance,
			double voucher, double afterSum, String remarks, Date endDate) {
		super();
		this.date = startdate;
		this.type = type;
		this.state = state;
		ID = D;
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
	
	
	
}
