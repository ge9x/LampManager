package dataimpl.financedataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillPO;
import po.BillPO;
import po.CashBillPO;
import util.BillState;
import util.BillType;
import util.ResultMessage;

public class FinanceDataServiceImpl implements FinanceDataService{
	private static FinanceDataServiceImpl financeDataServiceImpl;
	private DataHelper<CashBillPO> cashBillDataHelper;
	private DataHelper<AccountBillPO> accountBillDataHelper;
	
	public static FinanceDataServiceImpl getInstance(){
		if(financeDataServiceImpl == null){
			financeDataServiceImpl = new FinanceDataServiceImpl();
		}
		return financeDataServiceImpl;
	}
	
	private FinanceDataServiceImpl(){
		this.cashBillDataHelper = new HibernateDataHelper<CashBillPO>(CashBillPO.class);
		this.accountBillDataHelper = new HibernateDataHelper<AccountBillPO>(AccountBillPO.class);
	}

	@Override
	public String getNewReceiptID() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewPaymentID() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewCashBillID() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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





}
