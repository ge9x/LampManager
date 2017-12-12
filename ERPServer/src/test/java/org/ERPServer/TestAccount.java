package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.accountdataimpl.AccountDataServiceImpl;
import dataservice.accountdataservice.AccountDataService;
import po.AccountPO;

public class TestAccount {

	public static void main(String[] args) {
		AccountDataService accountImpl = AccountDataServiceImpl.getInstance();
		try {
//			AccountPO po1 = new AccountPO("工商银行账户", 25000);
//			AccountPO po2 = new AccountPO("建设银行账户", 2500);
//			AccountPO po3 = new AccountPO("瑞士银行账户", 250);
//			accountImpl.addAccount(po1);
//			accountImpl.addAccount(po2);
//			accountImpl.addAccount(po3);
			
			ArrayList<AccountPO> all = accountImpl.show();
			System.out.println("共有" + all.size() + "条银行账户记录：");
			for(AccountPO po : all){
				System.out.println(po.getID() + " " + po.getName() + " " + po.getMoney());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
