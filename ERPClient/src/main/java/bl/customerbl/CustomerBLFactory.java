package bl.customerbl;

import blservice.customerblservice.CustomerBLService;
import blservice.customerblservice.CustomerInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class CustomerBLFactory {
	private static CustomerController customerController;

	public synchronized static CustomerBLService getBLService() {
		if (customerController == null) {
			customerController = new CustomerController();
		}
		return customerController;
	}

	public synchronized static CustomerInfo getInfo() {
		if (customerController == null) {
			customerController = new CustomerController();
		}
		return customerController;
	}
}
