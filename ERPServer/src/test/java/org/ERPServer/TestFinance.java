package org.ERPServer;

import java.rmi.RemoteException;

import dataimpl.financedataimpl.FinanceDataServiceImpl;
import dataservice.financedataservice.FinanceDataService;

public class TestFinance {

	public static void main(String[] args) {
		FinanceDataService financeImpl = FinanceDataServiceImpl.getInstance();
		try {
			System.out.println(financeImpl.getNewReceiptID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
