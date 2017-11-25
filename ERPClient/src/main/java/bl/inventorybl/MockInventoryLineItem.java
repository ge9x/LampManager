package bl.inventorybl;

import blservice.salesblservice.SalesInfo;
import util.InventoryListItemType;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryLineItem extends InventoryLineItem {

	public MockInventoryLineItem(SalesInfo salesInfo, InventoryListItemType inventoryListItemType, String goodsName,
			int numberDifference, int totalPrice){
		super(salesInfo, inventoryListItemType, goodsName, numberDifference, totalPrice);
	}

}
