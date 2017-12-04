package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataimpl.customerdataimpl.CustomerDataServiceImpl;
import dataservice.customerdataservice.CustomerDataService;
import po.CustomerPO;
import util.CustomerCategory;
import util.Level;

public class TestCustomer {
	public static void main(String[] args){
		CustomerDataService cusImpl=CustomerDataServiceImpl.getInstance();
		
		try{
			
//			第一次请取消这段注释，成功运行一次后再注释掉
		/**
			cusImpl.add(new CustomerPO(CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,400));
			cusImpl.add(new CustomerPO(CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,500));
		*/
			ArrayList<CustomerPO> cus=cusImpl.show();
			System.out.println("共有"+cus.size()+"条记录：");
			for(CustomerPO po:cus){
				System.out.println(po.getCustomerID()+" "+po.getCustomerName()+" "+po.getAddress());
			}
			System.out.println("查询结束");
		
			
			ArrayList<CustomerPO> cus1=cusImpl.findByKeywords("销售");
			System.out.println("共有"+cus1.size()+"条记录：");
			for(CustomerPO po:cus1){
				System.out.println(po.getCustomerID()+" "+po.getCustomerName()+" "+po.getAddress());
			}
			System.out.println("查询结束");
			
			/**
			cusImpl.init();
			ArrayList<CustomerPO> cus2=cusImpl.show();
			System.out.println("共有"+cus2.size()+"条记录");
			System.out.println("查询结束");
			*/
		}catch(RemoteException e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
	}
}
