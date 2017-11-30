package dataimpl.formdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.formdataservice.FormDataService;
import po.BillPO;
import po.CashBillPO;
import util.BillState;
import util.BillType;

/**
 * Created on 2017/11/30
 * @author 巽
 *
 */
public class FormDataServiceImpl implements FormDataService{
	private static FormDataServiceImpl formDataServiceImpl;
	private DataHelper<CashBillPO> cashBillDataHelper;
	
	public static FormDataServiceImpl getInstance(){
		if(formDataServiceImpl == null){
			formDataServiceImpl = new FormDataServiceImpl();
		}
		return formDataServiceImpl;
	}
	
	private FormDataServiceImpl(){
		this.cashBillDataHelper = new HibernateDataHelper<CashBillPO>(CashBillPO.class);
	}

	@Override
	public ArrayList<BillPO> findBillByType(BillType typpe) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillPO findBillByID(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BillPO> findBillByState(BillState state) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BillPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllIDByType(BillType type) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomer(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSalesman(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInventory(String ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
