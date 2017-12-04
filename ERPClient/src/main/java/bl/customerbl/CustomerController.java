package bl.customerbl;

import java.rmi.RemoteException;
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
		try {
			return customer.addCustomer(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public ResultMessage deleteCustomer(String customerID) {
		try {
			return customer.deleteCustomer(customerID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public ArrayList<CustomerVO> findCustomerByKeywords(String keywords) {
		try {
			return customer.findCustomerByKeywords(keywords);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<CustomerVO> findCustomerByCustomerID(String customerID) {
		try {
			return customer.findCustomerByCustomerID(customerID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResultMessage updateCustomer(CustomerVO vo) {
		try {
			return customer.updateCustomer(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	public ArrayList<CustomerVO> show() {
		try {
			return customer.show();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Integer> getAllCustomerID(){
		return customer.getAllCustomerID();
	}
	
	public ArrayList<String> getAllCustomerName(){
		return customer.getAllCustomerName();
	}
	
	public CustomerVO getCustomerByID(int ID){
		return customer.getCustomerByID(ID);
	}
	
}
