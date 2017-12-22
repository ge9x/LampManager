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
import po.InitClassificationPO;
import po.InitCustomerPO;
import po.InitGoodsPO;
import po.InitializationPO;
import util.CustomerCategory;
import util.Level;

public class TestInitialization {
	public static void main(String[] args){
		InitializationDataService initImpl=InitializationDataServiceImpl.getInstance();
		
		//			第一次请取消这段注释，成功运行一次后再注释掉
					InitCustomerPO c1=new InitCustomerPO(1, "00000001", CustomerCategory.PUR_AGENT.getValue(), Level.LEVEL_THREE.getValue(), "进货商1", "13579593893", "马群", "599000", "567@163.com", 300.0, 200.0, 100.0, "Bobule", 30);
					ArrayList<InitCustomerPO> initCustomerPOs=new ArrayList<>();
					initCustomerPOs.add(c1);
					
					InitAccountPO a1=new InitAccountPO(1,"aster", 1000);
					ArrayList<InitAccountPO> initAccountPOs=new ArrayList<>();
					initAccountPOs.add(a1);
					
					InitClassificationPO classification1=new InitClassificationPO(1, "日光灯");
					ArrayList<InitClassificationPO> initClassificationPOs=new ArrayList<>();
					initClassificationPOs.add(classification1);
					 
					InitGoodsPO g1=new InitGoodsPO(1, "苹果牌护眼灯", "m", 300.0, 200.0, 230.0, 240.0, 3,classification1);
					ArrayList<InitGoodsPO> initGoodsPOs=new ArrayList<>();
					initGoodsPOs.add(g1);
					
					InitializationPO initializationPO=new InitializationPO("2017-12-21", initCustomerPOs, initAccountPOs, initGoodsPOs, initClassificationPOs);
					initImpl.init(initializationPO);
					
					ArrayList<String> dates=initImpl.getAllInitDates();
					for(int i=0;i<dates.size();i++){
						System.out.println(dates.get(i));
					}
					System.out.println("开始查询：");
					//InitializationPO initializationPO=initImpl.getInitializationByDate("2017-12-21");
					System.out.println(initializationPO.getID()+" "+initializationPO.getDate());
					System.out.println("查询结束！");
			
//			        CustomerPO c2=new CustomerPO(CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
//							"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,400);
//			        ArrayList<CustomerPO> customerPOS1 = new ArrayList<CustomerPO>();
//			        customerPOS1.add(c2);
//			        cusImpl.add(c2);
//			        
//			        ClassificationPO classification2 = new ClassificationPO( "吊灯", null, new ArrayList<ClassificationPO>(), new ArrayList<GoodsPO>());
//			        ArrayList<ClassificationPO> classificationPOS1 = new ArrayList<ClassificationPO>();
//			        classificationPOS1.add(classification2);
//			        claImpl.add(classification2);
//			        
//			        GoodsPO goods2 = new GoodsPO("圣洁牌纯白落地灯", "M", null, 700, 250, 233.3, 250, 233.3,2);
//			        ArrayList<GoodsPO> goodsVOS1 = new ArrayList<GoodsPO>();
//			        goodsVOS1.add(goods2);
//			        goodsImpl.add(goods2);
//			        
			        //InitAccountPO po2 = new InitAccountPO(new Date().toString(),customerPOS1,null,goodsVOS1,classificationPOS1);
			        
			       // initImpl.init(po1);
			       // initImpl.init(po2);
			        
			    
	}
}
