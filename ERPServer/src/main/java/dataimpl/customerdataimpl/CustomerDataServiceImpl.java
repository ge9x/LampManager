package dataimpl.customerdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import util.Criterion;
import util.ResultMessage;

/**
 * Created on 2017/11/26
 * @author zlk
 *
 */

public class CustomerDataServiceImpl implements CustomerDataService{
	private static CustomerDataServiceImpl customerDataServiceImpl;
	private DataHelper<CustomerPO> customerDataHelper;
	
	public static CustomerDataServiceImpl getInstance(){
		if(customerDataServiceImpl == null){
			customerDataServiceImpl = new CustomerDataServiceImpl();
		}
		return customerDataServiceImpl;
	}
	
	private CustomerDataServiceImpl(){
		this.customerDataHelper = new HibernateDataHelper<CustomerPO>(CustomerPO.class);
	}


	public ResultMessage add(CustomerPO po) throws RemoteException {
		return customerDataHelper.save(po);
	}

	public ResultMessage delete(CustomerPO po) throws RemoteException {
		return customerDataHelper.delete(po);
	}

	public ArrayList<CustomerPO> findByCustomerID(int customerID) throws RemoteException {
		return customerDataHelper.fullyQuery("id", customerID);
	}

	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException {
		return customerDataHelper.fullyQuery("customerName", keywords);
	}

	public ResultMessage update(CustomerPO po) throws RemoteException {
		return customerDataHelper.update(po);
	}

	public ArrayList<CustomerPO> show() throws RemoteException {
		return customerDataHelper.multiQuery(new ArrayList<Criterion>());
	}

	public void init() throws RemoteException {
		ArrayList<CustomerPO> cus=customerDataServiceImpl.show();
		for(int i=0;i<cus.size();i++){
			customerDataHelper.delete(cus.get(i));
		}
	}

	public CustomerPO getCustomerData(int ID) throws RemoteException {
		return customerDataHelper.exactlyQuery("id", ID);
	}
	
	
}
