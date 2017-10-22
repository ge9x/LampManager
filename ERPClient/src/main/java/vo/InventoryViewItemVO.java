package vo;

import util.InventoryViewItemType;

/**
 * 一张单据中一种商品的库存变动条目<br><br>
 * Created on 2017/10/22
 * @author 巽
 *
 */
public class InventoryViewItemVO{
	/**
	 * 商品
	 */
	public GoodsVO goods;
	/**
	 * 库存变动类型
	 */
	public InventoryViewItemType type;
	/**
	 * 数量
	 */
	public int amount;
	/**
	 * 金额
	 */
	public double price;
	
	public InventoryViewItemVO(GoodsVO goods, InventoryViewItemType type, int amount, double price) {
		super();
		this.goods = goods;
		this.type = type;
		this.amount = amount;
		this.price = price;
	}
}

