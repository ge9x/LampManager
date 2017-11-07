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
	
	@Override
	public String getGoodsID() {
		return super.getGoodsID();
	}

	@Override
	public InventoryListItemType getInventoryListItemType() {
		return super.getInventoryListItemType();
	}

	@Override
	public String getGoodsName() {
		return super.getGoodsName();
	}

	@Override
	public int getNumberDifference() {
		return super.getNumberDifference();
	}

	@Override
	public int getTotalPrice() {
		return super.getTotalPrice();
	}

}
