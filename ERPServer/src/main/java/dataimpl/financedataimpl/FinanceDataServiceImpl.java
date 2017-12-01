package dataimpl.financedataimpl;

import java.rmi.RemoteException;
import java.time.LocalTime;
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
import util.Criterion;
import util.QueryMode;
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
	public int getNewReceiptID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.RECEIPT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalTime.now().toString(), QueryMode.FULL));
		return accountBillDataHelper.multiQuery(criteria).size() + 1;
	}

	@Override
	public int getNewPaymentID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.PAYMENT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalTime.now().toString(), QueryMode.FULL));
		return accountBillDataHelper.multiQuery(criteria).size() + 1;
	}

	@Override
	public int getNewCashBillID() throws RemoteException {
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

}
