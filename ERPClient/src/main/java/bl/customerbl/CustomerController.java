package bl.customerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.customerblservice.CustomerBLService;
import blservice.customerblservice.CustomerInfo;
import dataservice.customerdataservice.CustomerDataService;
import util.ResultMessage;
import util.UserLimits;
import vo.CustomerVO;
import vo.UserVO;

/**
 * Created by zlk on 2017/11/5
 */

public class CustomerController implements CustomerBLService,CustomerInfo{
	
	private Customer customer;
	
	public CustomerController(){
		customer=new Customer();
	}

	public String getNewCustomerID() {
		try {
			return customer.getNewCustomerID();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
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

	public ResultMessage raiseCustomerReceive(int customerID, double amount) {
		return customer.raiseCustomerReceive(customerID, amount);
	}

	public ResultMessage reduceCustomerReceive(int customerID, double amount) {
		return customer.reduceCustomerReceive(customerID, amount);
	}

	public ResultMessage raiseCustomerPay(int customerID, double amount) {
		return customer.raiseCustomerPay(customerID, amount);
	}

	public ResultMessage reduceCustomerPay(int customerID, double amount) {
		return customer.raiseCustomerPay(customerID, amount);
	}

	public ArrayList<CustomerVO> getAllSupplier()  {
		try {
			return customer.getAllSupplier();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<CustomerVO> getAllSeller(){
		try {
			return customer.getAllSeller();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserVO> getAllSalesman() {
		return customer.getAllSalesman();
	}

	@Override
	public UserLimits getCurrentUserLimit() {
		return customer.getCurrentUserLimit();
	}

	@Override
	public ArrayList<CustomerVO> findCustomer(String input) {
		try {
			return customer.findCustomer(input);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
