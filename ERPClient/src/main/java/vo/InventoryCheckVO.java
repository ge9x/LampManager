package vo;

import java.util.Date;
import java.util.HashMap;

/**
 * Created on 2017/10/21
 * @author 巽
 *
 */
public class InventoryCheckVO {
	/**
	 * 盘点日期
	 */
	public Date date;
	/**
	 * 每个商品->库存均价
	 */
	public HashMap<GoodsVO, Double> averagePrice;
	
	public InventoryCheckVO(Date date, HashMap<GoodsVO, Double> averagePrice) {
		super();
		this.date = date;
		this.averagePrice = averagePrice;
	}
}
