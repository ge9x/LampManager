package bl.inventorybl;

import java.util.ArrayList;

import blservice.salesblservice.SalesInfo;
import util.ResultMessage;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryList extends InventoryList {

	@Override
	public ResultMessage add(SalesInfo salesInfo) {
		return ResultMessage.FAILED;
	}

	@Override
	public ResultMessage delete(SalesInfo salesInfo) {
		return ResultMessage.FAILED;
	}

	@Override
	public ArrayList<InventoryLineItem> getList() {
		return super.getList();
	}

}
