package bl.inventorybl;

import blservice.salesblservice.SalesInfo;
import util.InventoryListItemType;

/**
 * Created on 2017/11/5
 * @author 巽
 *
 */
public class InventoryLineItem {
	String goodsID;
	InventoryListItemType inventoryListItemType;
	String goodsName;
	int numberDifference;
	int totalPrice;
	
	public InventoryLineItem(SalesInfo salesInfo, InventoryListItemType inventoryListItemType, String goodsName,
			int numberDifference, int totalPrice) {
		super();
		this.inventoryListItemType = inventoryListItemType;
		this.goodsName = goodsName;
		this.numberDifference = numberDifference;
		this.totalPrice = totalPrice;
	}
	
}
