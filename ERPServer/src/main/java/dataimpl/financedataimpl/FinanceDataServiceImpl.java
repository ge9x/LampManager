package dataimpl.financedataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.financedataservice.FinanceDataService;
import po.BillPO;
import po.CashBillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

public class FinanceDataServiceImpl implements FinanceDataService{
	private static FinanceDataServiceImpl financeDataServiceImpl;
	private DataHelper<CashBillPO> cashBillDataHelper;
	
	public static FinanceDataServiceImpl getInstance(){
		if(financeDataServiceImpl == null){
			financeDataServiceImpl = new FinanceDataServiceImpl();
		}
		return financeDataServiceImpl;
	}
	
	private FinanceDataServiceImpl(){
		this.cashBillDataHelper = new HibernateDataHelper<CashBillPO>(CashBillPO.class);
	}

	@Override
	public int getNewReceiptID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNewPaymentID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNewCashBillID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultMessage addBill(BillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteBill(BillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage updateBill(BillPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BillPO> findBillByType(BillType typpe) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillPO findBillByID(int ID) throws RemoteException {
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
	public Date getDate(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCustomer(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSalesman(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInventory(int ID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
