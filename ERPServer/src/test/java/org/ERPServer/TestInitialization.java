package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import dataimpl.accountdataimpl.AccountDataServiceImpl;
import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataimpl.customerdataimpl.CustomerDataServiceImpl;
import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataimpl.initializationdataimpl.InitializationDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import dataservice.customerdataservice.CustomerDataService;
import dataservice.goodsdataservice.GoodsDataService;
import dataservice.initializationdataservice.InitializationDataService;
import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import po.InitAccountPO;
import util.CustomerCategory;
import util.Level;

public class TestInitialization {
	public static void main(String[] args){
		InitializationDataService initImpl=InitializationDataServiceImpl.getInstance();
		CustomerDataService cusImpl=CustomerDataServiceImpl.getInstance();
		ClassificationDataService claImpl=ClassificationDataServiceImpl.getInstance();
		AccountDataServiceImpl accImpl=AccountDataServiceImpl.getInstance();
		GoodsDataService goodsImpl=GoodsDataServiceImpl.getInstance();
		
		
		try{
		//			第一次请取消这段注释，成功运行一次后再注释掉
					CustomerPO c1=new CustomerPO( CustomerCategory.PUR_AGENT, Level.LEVEL_FIVE,"进货商2","15244358373",
			                "南京新街口","421001","34s@163.com",0.8,0.0,2000.0,"业务员2",50.0,100);
			        ArrayList<CustomerPO> customerPOS = new ArrayList<CustomerPO>();
			        customerPOS.add(c1);
			        cusImpl.add(c1);
		
			        ClassificationPO classification1 = new ClassificationPO( "霓虹灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
			        ArrayList<ClassificationPO> classificationPOS = new ArrayList<ClassificationPO>();
			        classificationPOS.add(classification1);
			        claImpl.add(classification1);
		
			        GoodsPO goods1 = new GoodsPO( "后现代主义七彩霓虹灯", "LLL", null, 7, 3, 23333.3, 250000, 2000.0,1);
			        ArrayList<GoodsPO> goodsVOS = new ArrayList<GoodsPO>();
			        goodsVOS.add(goods1);
			        goodsImpl.add(goods1);
		
			        InitAccountPO po1 = new InitAccountPO(new Date().toString(),customerPOS,null,goodsVOS,classificationPOS);
			        
			        CustomerPO c2=new CustomerPO(CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
							"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,400);
			        ArrayList<CustomerPO> customerPOS1 = new ArrayList<CustomerPO>();
			        customerPOS1.add(c2);
			        cusImpl.add(c2);
			        
			        ClassificationPO classification2 = new ClassificationPO( "吊灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
			        ArrayList<ClassificationPO> classificationPOS1 = new ArrayList<ClassificationPO>();
			        classificationPOS1.add(classification2);
			        claImpl.add(classification2);
			        
			        GoodsPO goods2 = new GoodsPO("圣洁牌纯白落地灯", "M", null, 700, 250, 233.3, 250, 233.3,2);
			        ArrayList<GoodsPO> goodsVOS1 = new ArrayList<GoodsPO>();
			        goodsVOS1.add(goods2);
			        goodsImpl.add(goods2);
			        
			        InitAccountPO po2 = new InitAccountPO(new Date().toString(),customerPOS1,null,goodsVOS1,classificationPOS1);
			        
			        initImpl.init(po1);
			        initImpl.init(po2);
			        
			        System.out.println("显示期初建账信息：");
			        initImpl.show();
		}catch(RemoteException e){
			System.out.println("Exception!");
			e.printStackTrace();
		}
	}
}
