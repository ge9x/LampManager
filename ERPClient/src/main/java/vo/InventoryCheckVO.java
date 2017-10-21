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
	Date date;
	/**
	 * 每个商品->库存均价
	 */
	HashMap<GoodsVO, Double> averagePrice;
}
