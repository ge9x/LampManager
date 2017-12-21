package bl.inventorybl;

import util.InventoryListItemType;

/**
 * Created on 2017/11/5
 * 
 * @author 巽
 *
 */
public class InventoryLineItem {
	String date;
	String goodsID;
	String goodsName;
	String goodsModel;
	InventoryListItemType inventoryListItemType;
	int numberDifference;
	double totalPrice;
	
	public InventoryLineItem(String date, String goodsID, String goodsName, String goodsModel,
			InventoryListItemType inventoryListItemType, int numberDifference, double totalPrice) {
		super();
		this.date = date;
		this.goodsID = goodsID;
		this.goodsName = goodsName;
		this.goodsModel = goodsModel;
		this.inventoryListItemType = inventoryListItemType;
		this.numberDifference = numberDifference;
		this.totalPrice = totalPrice;
	}

}
