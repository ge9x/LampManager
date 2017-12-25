package bl.inventorybl;

import java.util.ArrayList;
import java.util.HashMap;

import util.InventoryListItemType;
import vo.GoodsVO;
import vo.InventoryBillVO;
import vo.InventoryViewItemVO;
import vo.InventoryViewVO;

/**
 * Created on 2017/11/7
 * @author 巽
 *
 */
public class MockInventoryList extends InventoryList {

	@SuppressWarnings("deprecation")
	@Override
	public InventoryViewVO show(String startDate, String endDate, String inventory, ArrayList<InventoryBillVO> inventoryBillVOs) {
		ArrayList<InventoryViewItemVO> item = new ArrayList<>();
		GoodsVO goods = new GoodsVO("0100001", "圣洁牌经典黑白配台灯", "L", null, null, 200, 25, 250, 2500, 250, 2500);
		double price = 233;
		InventoryViewItemVO viewItemVO = new InventoryViewItemVO("2017-12-21", goods, InventoryListItemType.IN, 200, price);
		item.add(viewItemVO);
		HashMap<GoodsVO, Double> total = new HashMap<>();
		total.put(goods, price);
		InventoryViewVO ret = new InventoryViewVO(startDate, endDate, inventory, item, total);
		return ret;
	}

}
