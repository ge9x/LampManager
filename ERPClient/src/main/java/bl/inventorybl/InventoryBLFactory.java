package bl.inventorybl;

import blservice.inventoryblservice.InventoryBLService;
import blservice.inventoryblservice.InventoryInfo;

/**
 * Created on 2017/12/29
 * @author 巽
 *
 */
public class InventoryBLFactory {
	private static InventoryController inventoryController;

	public synchronized static InventoryBLService getBLService() {
		if (inventoryController == null) {
			inventoryController = new InventoryController();
		}
		return inventoryController;
	}

	public synchronized static InventoryInfo getInfo() {
		if (inventoryController == null) {
			inventoryController = new InventoryController();
		}
		return inventoryController;
	}
}
