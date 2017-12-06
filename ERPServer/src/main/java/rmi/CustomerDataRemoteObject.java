package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.customerdataimpl.CustomerDataServiceImpl;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import util.ResultMessage;

public class CustomerDataRemoteObject extends UnicastRemoteObject implements CustomerDataService{
	private CustomerDataService customerDataService;
	
	protected CustomerDataRemoteObject() throws RemoteException{
		customerDataService=CustomerDataServiceImpl.getInstance();
	}

	@Override
	public ResultMessage add(CustomerPO po) throws RemoteException {
		return customerDataService.add(po);
	}

	@Override
	public ResultMessage delete(CustomerPO po) throws RemoteException {
		return customerDataService.delete(po);
	}

	@Override
	public ArrayList<CustomerPO> findByCustomerID(int customerID) throws RemoteException {
		return customerDataService.findByCustomerID(customerID);
	}

	@Override
	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException {
		return customerDataService.findByKeywords(keywords);
	}

	@Override
	public ResultMessage update(CustomerPO po) throws RemoteException {
		return customerDataService.update(po);
	}

	@Override
	public ArrayList<CustomerPO> show() throws RemoteException {
		return customerDataService.show();
	}

	@Override
	public void init() throws RemoteException {
		customerDataService.init();
	}

	@Override
	public CustomerPO getCustomerData(int ID) throws RemoteException {
		return customerDataService.getCustomerData(ID);
	}

	@Override
	public String getNewCustomerID() throws RemoteException {
		return customerDataService.getNewCustomerID();
	}
	
}
