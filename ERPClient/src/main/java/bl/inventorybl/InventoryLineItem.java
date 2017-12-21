package bl.inventorybl;

import util.InventoryListItemType;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class InventoryLineItem {
	String goodsID;
	InventoryListItemType inventoryListItemType;
	String goodsName;
	int numberDifference;
	int totalPrice;

	public InventoryLineItem(InventoryListItemType inventoryListItemType, String goodsName, int numberDifference,
			int totalPrice) {
		this.inventoryListItemType = inventoryListItemType;
		this.goodsName = goodsName;
		this.numberDifference = numberDifference;
		this.totalPrice = totalPrice;
	}

}
