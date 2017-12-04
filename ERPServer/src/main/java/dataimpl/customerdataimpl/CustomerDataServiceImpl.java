package dataimpl.customerdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.classificationdataservice.ClassificationDataService;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import util.BillType;
import util.Criterion;
import util.CustomerCategory;
import util.Level;
import util.QueryMode;
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
		return customerDataHelper.fuzzyQuery("customerID", Integer.toString(customerID));
	}

	public ArrayList<CustomerPO> findByKeywords(String keywords) throws RemoteException {
		ArrayList<Criterion> criteria=new ArrayList<Criterion>();
		switch (keywords) {
		case "进货商":
			criteria.add(
					new Criterion("category", CustomerCategory.PUR_AGENT,QueryMode.FULL)
					);
			return customerDataHelper.multiQuery(criteria);
		case "销售商":
			criteria.add(
					new Criterion("category", CustomerCategory.SELLER,QueryMode.FULL)
					);
		default:
			criteria.add(
					new Criterion(
					new Criterion("category",keywords,QueryMode.FUZZY),
				    new Criterion(
					new Criterion("level",keywords,QueryMode.FUZZY),
					new Criterion(
					new Criterion("customerName",keywords,QueryMode.FUZZY),
					new Criterion(
					new Criterion("phone",keywords,QueryMode.FUZZY),
					new Criterion(
					new Criterion("address",keywords,QueryMode.FUZZY),
					new Criterion(
					new Criterion("mail",keywords,QueryMode.FUZZY),
					new Criterion("salesman",keywords,QueryMode.FUZZY)																				)
																	)
															)
													)	
					                        )
									)
	                      	);
			return customerDataHelper.multiQuery(criteria);
		}
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
