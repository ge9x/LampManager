package po;

import java.util.ArrayList;

/**
 * created by zlk on 2017/10/20
 */

public class SalesPO {
     /**时间*/
	private String time;
	/**单据类型*/
	private String kind;
	/**单据状态*/
	private String state;
	/**单据编号*/
	private String salesID;
	/**客户*/
	private String customerName;
	/**业务员*/
	private String salesman;
	/**操作员*/
	private String user;
	/**仓库*/
	private String inventory;
	/**商品列表清单*/
	private ArrayList<GoodsItemPO> goods;
	
}
