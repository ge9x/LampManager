package org.ERPServer;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;
import po.AccountBillItemPO;
import po.AccountBillPO;
import po.BillPO;
import po.CashBillItemPO;
import po.CashBillPO;
import util.BillState;
import util.BillType;

public class TestFinance {

	public static void main(String[] args) {
		FinanceDataService financeImpl = FinanceDataServiceImpl.getInstance();
		try {
//			String newReciptID = financeImpl.getNewReceiptID();
//			String newPaymentID = financeImpl.getNewPaymentID();
			String newCashID = financeImpl.getNewCashBillID();
//			ArrayList<AccountBillItemPO> reciptItem = new ArrayList<>();
//			reciptItem.add(new AccountBillItemPO(1, 200, "借钱"));
//			ArrayList<AccountBillItemPO> paymentItem = new ArrayList<>();
//			paymentItem.add(new AccountBillItemPO(1, 200, "还钱"));
			ArrayList<CashBillItemPO> cashItem = new ArrayList<>();
			cashItem.add(new CashBillItemPO("出差费报销", 2333, "还是公款吃喝——sj"));
//			AccountBillPO recipt = new AccountBillPO(LocalTime.now().toString(), BillType.RECEIPT, BillState.DRAFT, 1, "sj", reciptItem, Integer.parseInt(newReciptID.split("-")[2]));
//			AccountBillPO payment = new AccountBillPO(LocalTime.now().toString(), BillType.RECEIPT, BillState.DRAFT, 1, "sj", paymentItem, Integer.parseInt(newPaymentID.split("-")[2]));
			CashBillPO cash = new CashBillPO(LocalDate.now().toString(), BillType.CASH, BillState.SUBMITTED, "sj", 2, cashItem, Integer.parseInt(newCashID.split("-")[2]));
			System.out.println("CashBillID:" + cash.getID());
			System.out.println("CashBillItemID:" + cashItem.get(0).getID());
//			financeImpl.addBill(recipt);
//			financeImpl.addBill(payment);
			financeImpl.addBill(cash);

			System.out.println("CashBillID:" + cash.getID());
			System.out.println("CashBillItemID:" + cashItem.get(0).getID());
			cashItem.add(new CashBillItemPO("小组聚餐费报销", 666, "当然是公款吃喝——sj"));
			cash.setCashBillItemPOS(cashItem);
			financeImpl.updateBill(cash);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
