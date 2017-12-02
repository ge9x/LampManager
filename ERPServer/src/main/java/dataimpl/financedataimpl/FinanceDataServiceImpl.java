package dataimpl.financedataimpl;

import java.rmi.RemoteException;
import java.time.LocalTime;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillPO;
import po.BillPO;
import po.CashBillPO;
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
	public String getNewReceiptID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.RECEIPT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalTime.now().toString(), QueryMode.FULL));
		int num = accountBillDataHelper.multiQuery(criteria).size() + 1;
		return null;
	}

	@Override
	public String getNewPaymentID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.PAYMENT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalTime.now().toString(), QueryMode.FULL));
		int num = accountBillDataHelper.multiQuery(criteria).size() + 1;
		return null;
	}

	@Override
	public String getNewCashBillID() throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage addBill(BillPO po) throws RemoteException {
		switch(po.getType()){
		case RECEIPT: case PAYMENT:
			accountBillDataHelper.save((AccountBillPO)po);
			return ResultMessage.SUCCESS;
		case CASH:
			cashBillDataHelper.save((CashBillPO)po);
			return ResultMessage.SUCCESS;
		default:
			System.out.println("Bug：请求添加未知类型的单据");
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage deleteBill(BillPO po) throws RemoteException {
		switch(po.getType()){
		case RECEIPT: case PAYMENT:
			accountBillDataHelper.delete((AccountBillPO)po);
			return ResultMessage.SUCCESS;
		case CASH:
			cashBillDataHelper.delete((CashBillPO)po);
			return ResultMessage.SUCCESS;
		default:
			System.out.println("Bug：请求删除未知类型的单据");
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage updateBill(BillPO po) throws RemoteException {
		switch(po.getType()){
		case RECEIPT: case PAYMENT:
			accountBillDataHelper.update((AccountBillPO)po);
			return ResultMessage.SUCCESS;
		case CASH:
			cashBillDataHelper.update((CashBillPO)po);
			return ResultMessage.SUCCESS;
		default:
			System.out.println("Bug：请求修改未知类型的单据");
			return ResultMessage.ERROR;
		}
	}

}
