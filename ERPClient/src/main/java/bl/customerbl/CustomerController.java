package bl.customerbl;

import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import util.ResultMessage;
import vo.CustomerVO;

/**
 * Created by zlk on 2017/11/5
 */

public class CustomerController implements CustomerBLService{
	
	private Customer customer;
	
	public CustomerController(){
		customer=new Customer();
	}

	@Override
	public String getNewCustomerID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteCustomer(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateCustomer(CustomerVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
