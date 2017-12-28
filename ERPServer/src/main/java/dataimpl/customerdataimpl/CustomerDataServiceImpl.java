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
    @Override
	public String getNewCustomerID() throws RemoteException {
		String res=String.format("%02d", customerDataHelper.count() + 1);
		int len=res.length();
		for(int i=0;i<8-len;i++){
			res="0"+res;
		}
		return res;		
	}

	@Override
	public ArrayList<CustomerPO> findCustomer(String input) throws RemoteException {
		ArrayList<Criterion> criteria=new ArrayList<Criterion>();
		criteria.add(
				new Criterion(
				new Criterion("customerID", input,QueryMode.FUZZY),
				new Criterion(
				new Criterion("category",input,QueryMode.FUZZY),
			    new Criterion(
				new Criterion("level",input,QueryMode.FUZZY),
				new Criterion(
				new Criterion("customerName",input,QueryMode.FUZZY),
				new Criterion(
				new Criterion("phone",input,QueryMode.FUZZY),
				new Criterion(
				new Criterion("address",input,QueryMode.FUZZY),
				new Criterion(
				new Criterion("mail",input,QueryMode.FUZZY),
				new Criterion("salesman",input,QueryMode.FUZZY)																				)
																)
														)
												)	
				                        )
								)
				       )
                );
		return customerDataHelper.multiQuery(criteria);
	}
	
	
}
