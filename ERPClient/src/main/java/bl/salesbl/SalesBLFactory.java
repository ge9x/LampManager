package bl.salesbl;

import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesBLService;
import blservice.salesblservice.SalesInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class SalesBLFactory {
	private static SalesController salesController;
	private static PurchaseController purchaseController;

	public synchronized static SalesBLService getBargainBLService() {
		if (salesController == null) {
			salesController = new SalesController();
		}
		return salesController;
	}

	public synchronized static SalesInfo getBargainInfo() {
		if (salesController == null) {
			salesController = new SalesController();
		}
		return salesController;
	}

	public synchronized static PurchaseInfo getCustomerInfo() {
		if (purchaseController == null) {
			purchaseController = new PurchaseController();
		}
		return purchaseController;
	}
}
