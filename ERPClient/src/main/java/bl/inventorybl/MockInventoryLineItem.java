package bl.inventorybl;

import util.InventoryListItemType;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryLineItem extends InventoryLineItem {

	public MockInventoryLineItem(InventoryListItemType inventoryListItemType, String goodsName,
			int numberDifference, int totalPrice){
		super(inventoryListItemType, goodsName, numberDifference, totalPrice);
	}

}
