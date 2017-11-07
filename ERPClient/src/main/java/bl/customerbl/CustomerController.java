package bl.customerbl;

import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import blservice.customerblservice.CustomerInfo;
import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public class CustomerController implements CustomerBLService,CustomerInfo{
	
	private Customer customer;
	
	public CustomerController(){
		customer=new Customer();
	}

	public String getNewCustomerID() {
		return null;
	}

	public ResultMessage addCustomer(CustomerVO vo) {
		return null;
	}

	public ResultMessage deleteCustomer(String customerID) {
		return null;
	}

	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		return null;
	}

	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		return null;
	}

	public ResultMessage updateCustomer(CustomerVO vo) {
		return null;
	}
	
	public ArrayList<String> getAllCustomerName() {
		return null;
	}

	public CustomerVO getCustomerByName() {
		return null;
	}

	public CustomerVO getCustomerByID() {
		return null;
	}
	
}
