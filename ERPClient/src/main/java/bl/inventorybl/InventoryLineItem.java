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
	
	public String getGoodsID() {
		return goodsID;
	}
	public InventoryListItemType getInventoryListItemType() {
		return inventoryListItemType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public int getNumberDifference() {
		return numberDifference;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	
}
