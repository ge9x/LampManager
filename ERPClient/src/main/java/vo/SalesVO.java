package vo;

import java.sql.Date;
import java.util.ArrayList;

import util.BillState;
import util.BillType;

public class SalesVO {
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
	
	
	public SalesVO(Date date, BillType type, BillState state, String iD, String customer, String salesman,
			UserPosition user, String inventory, ArrayList<GoodsItemVO> goodsItem, double beforeSum, double allowance,
			double voucher, double afterSum, String remarks, Date endDate) {
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
	
	
	
}
