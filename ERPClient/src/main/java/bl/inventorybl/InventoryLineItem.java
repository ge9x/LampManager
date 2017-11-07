package bl.inventorybl;

import blservice.salesblservice.SalesInfo;
import util.InventoryListItemType;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryLineItem {
	private String goodsID;
	private InventoryListItemType inventoryListItemType;
	private String goodsName;
	private int numberDifference;
	private int totalPrice;
	
	public InventoryLineItem(SalesInfo salesInfo, InventoryListItemType inventoryListItemType, String goodsName,
			int numberDifference, int totalPrice) {
		super();
		this.inventoryListItemType = inventoryListItemType;
		this.goodsName = goodsName;
		this.numberDifference = numberDifference;
		this.totalPrice = totalPrice;
	}

	/**
	 * 得到商品的ID
	 * @return 商品的ID
	 */
	public String getGoodsID() {
		return goodsID;
	}
	
	/**
	 * 得到库存变动的类型
	 * @return 库存变动的类型
	 */
	public InventoryListItemType getInventoryListItemType() {
		return inventoryListItemType;
	}
	
	/**
	 * 得到商品的名称
	 * @return 商品的名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * 得到商品变动的数量（必为正数，通过库存变动类型指示增/减）
	 * @return 商品变动的数量
	 */
	public int getNumberDifference() {
		return numberDifference;
	}
	
	/**
	 * 得到变动的商品的总金额
	 * @return 变动的商品的总金额
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
	
}
