package bl.inventorybl;

import util.InventoryListItemType;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryLineItem extends InventoryLineItem {

	public MockInventoryLineItem(String date, String goodsID, String goodsName, String goodsModel, InventoryListItemType inventoryListItemType,
			int numberDifference, double totalPrice){
		super(date, goodsID, goodsName, goodsModel, inventoryListItemType, numberDifference, totalPrice);
	}

}
