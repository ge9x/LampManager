package dataimpl.financedataimpl;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.BillPO;
import po.CashBillItemPO;
import po.CashBillPO;
import util.BillType;
import util.Criterion;
import util.QueryMode;
import util.ResultMessage;

/**
 * Created on 2017/11/30
 * @author 巽
 *
 */
public class FinanceDataServiceImpl implements FinanceDataService{
	private static FinanceDataServiceImpl financeDataServiceImpl;
	private DataHelper<AccountBillPO> accountBillDataHelper;
	private DataHelper<AccountBillItemPO> accountBillItemDataHelper;
	private DataHelper<CashBillPO> cashBillDataHelper;
	private DataHelper<CashBillItemPO> cashBillItemDataHelper;
	
	public static FinanceDataServiceImpl getInstance(){
		if(financeDataServiceImpl == null){
			financeDataServiceImpl = new FinanceDataServiceImpl();
		}
		return financeDataServiceImpl;
	}
	
	private FinanceDataServiceImpl(){
		this.accountBillDataHelper = new HibernateDataHelper<AccountBillPO>(AccountBillPO.class);
		this.accountBillItemDataHelper = new HibernateDataHelper<AccountBillItemPO>(AccountBillItemPO.class);
		this.cashBillDataHelper = new HibernateDataHelper<CashBillPO>(CashBillPO.class);
		this.cashBillItemDataHelper = new HibernateDataHelper<CashBillItemPO>(CashBillItemPO.class);
	}

	@Override
	public String getNewReceiptID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.RECEIPT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = accountBillDataHelper.multiQuery(criteria).size() + 1;
		return BillType.RECEIPT.getAcronym() + "-" + LocalDate.now().toString() + "-" + String.format("%05d", num);
	}

	@Override
	public String getNewPaymentID() throws RemoteException {
		ArrayList<Criterion> criteria = new ArrayList<>();
		criteria.add(new Criterion("type", BillType.PAYMENT, QueryMode.FULL));
		criteria.add(new Criterion("date", LocalDate.now().toString(), QueryMode.FULL));
		int num = accountBillDataHelper.multiQuery(criteria).size() + 1;
		return BillType.PAYMENT.getAcronym() + "-" + LocalDate.now().toString() + "-" + String.format("%05d", num);
	}

	@Override
	public String getNewCashBillID() throws RemoteException {
		int num = cashBillDataHelper.fullyQuery("date", LocalDate.now().toString()).size();
		return BillType.CASH.getAcronym() + "-" + LocalDate.now().toString() + "-" + String.format("%05d", num);
	}

	@Override
	public ResultMessage addBill(BillPO po) throws RemoteException {
		switch(po.getType()){
		case RECEIPT: case PAYMENT:
			AccountBillPO apo = (AccountBillPO) po;
			for(AccountBillItemPO item : apo.getAccountBillItemPOS()){
				accountBillItemDataHelper.save(item);
			}
			accountBillDataHelper.save(apo);
			return ResultMessage.SUCCESS;
		case CASH:
			CashBillPO cpo = (CashBillPO) po;
			for(CashBillItemPO item : cpo.getCashBillItemPOS()){
				cashBillItemDataHelper.save(item);
			}
			cashBillDataHelper.save(cpo);
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
			AccountBillPO apo = (AccountBillPO) po;
			for(AccountBillItemPO item : apo.getAccountBillItemPOS()){
				if(item.getID()==0){
					accountBillItemDataHelper.save(item);
				}
			}
			accountBillDataHelper.update(apo);
			return ResultMessage.SUCCESS;
		case CASH:
			CashBillPO cpo = (CashBillPO) po;
			for(CashBillItemPO item : cpo.getCashBillItemPOS()){
				if(item.getID()==0){
					cashBillItemDataHelper.save(item);
				}
			}
			cashBillDataHelper.update(cpo);
			return ResultMessage.SUCCESS;
		default:
			System.out.println("Bug：请求修改未知类型的单据");
			return ResultMessage.ERROR;
		}
	}

}
