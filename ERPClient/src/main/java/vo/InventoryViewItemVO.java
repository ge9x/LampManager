package vo;

import util.InventoryListItemType;

/**
 * 一张单据中一种商品的库存变动条目<br><br>
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class InventoryViewItemVO{
	/**
	 * 变动日期
	 */
	public String date;
	/**
	 * 商品
	 */
	public GoodsVO goods;
	/**
	 * 库存变动类型
	 */
	public InventoryListItemType type;
	/**
	 * 数量
	 */
	public int amount;
	/**
	 * 金额
	 */
	public double price;
	
	public InventoryViewItemVO(String date, GoodsVO goods, InventoryListItemType type, int amount, double price) {
		this.date = date;
		this.goods = goods;
		this.type = type;
		this.amount = amount;
		this.price = price;
	}
}

