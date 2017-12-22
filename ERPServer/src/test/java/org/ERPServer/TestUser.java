package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.userdataimpl.UserDataServiceImpl;
import dataservice.userdataservice.UserDataService;
import po.UserPO;

public class TestUser {

	public static void main(String[] args) {
		UserDataService userImpl = UserDataServiceImpl.getInstance();
		try {
//			UserPO po1 = new UserPO("admin", "root", UserPosition.ADMIN, UserLimits.MANAGER);
//			UserPO po2 = new UserPO("shengjie", "yueshengjie", UserPosition.FINANCIAL_STAFF, UserLimits.STAFF);
//			UserPO po3 = new UserPO("dominating", "Xun", UserPosition.INVENTORY_STAFF, UserLimits.STAFF);
//			userImpl.add(po1);
//			userImpl.add(po2);
//			userImpl.add(po3);
			
			UserPO found = userImpl.find("00002");
			System.out.println("查找ID为00002的用户：");
			System.out.println(found.getUserID() + " " + found.getName() + " " + found.getPassword());
			ArrayList<UserPO> fuzzyFound = userImpl.findUsersByID("02");
			System.out.println("查找ID里有02的用户：" + fuzzyFound.size() + "台");
			for(UserPO po : fuzzyFound){
				System.out.println(po.getUserID() + " " + po.getName() + " " + po.getPassword());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
