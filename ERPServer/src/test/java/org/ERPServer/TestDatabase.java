package org.ERPServer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dataimpl.classificationdataimpl.ClassificationDataServiceImpl;
import dataimpl.customerdataimpl.CustomerDataServiceImpl;
//import dataimpl.goodsdataimpl.GoodsDataServiceImpl;
import dataservice.classificationdataservice.ClassificationDataService;
import dataservice.customerdataservice.CustomerDataService;
import dataservice.goodsdataservice.GoodsDataService;
import po.ClassificationPO;
import po.CustomerPO;
import po.GoodsPO;
import util.CustomerCategory;
import util.Level;

public class TestDatabase {
	SessionFactory sf;
	
	public TestDatabase(){
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	private Session setUp(){
		Session s = sf.openSession();
		s.beginTransaction();
		return s;
	}
	
	private void end(Session s){
		s.getTransaction().commit();
		s.close();
	}
	
	public void testClassification(){
		Session s = setUp();
////		第一次使用请取消这段注释，成功运行一次后再注释掉
//		ClassificationPO clapo = new ClassificationPO();
//		clapo.setName("灯");
//		GoodsPO gpo = new GoodsPO("圣洁牌经典黑白款落地灯", "L", null, 500, 50, 233.3, 250, 233.3, 250);
//		List<GoodsPO> goods = new ArrayList<GoodsPO>();
//		goods.add(gpo);
//		clapo.setGoods(goods);
//		s.save(clapo);
//		s.save(gpo);
//		end(s);
//		s = setUp();
		
		ClassificationDataService claImpl = ClassificationDataServiceImpl.getInstance();
		
		ClassificationPO father;
		try {
			father = claImpl.find("01");
			claImpl.add(new ClassificationPO("落地灯", father, null, null));
			
			ArrayList<ClassificationPO> claPOs = claImpl.show();
			System.out.println("共有" + claPOs.size() + "条记录：");
			for(ClassificationPO po : claPOs){
				System.out.println(po.getId() + " " + po.getName());
			}
			System.out.println("查询结束");
		} catch (RemoteException e) {
			System.out.println("Exception！");
			e.printStackTrace();
		}
		end(s);
	}
	
	public void testGoods(){
		Session session = setUp();
		
		//GoodsDataService goodsImpl = GoodsDataServiceImpl.getInstance();
		
		end(session);
	}
	
	public void testCustomer(){
		Session s = setUp();
		CustomerDataService customerImpl=CustomerDataServiceImpl.getInstance();
		
		try{	
			customerImpl.add(new CustomerPO(CustomerCategory.SELLER,Level.LEVEL_FIVE,"金主","15545786610",
					"南京仙林大学城","421000","ddl@163.com",1.0,10000.0,0.0,"业务员1",125.0,400));
			customerImpl.add(new CustomerPO(CustomerCategory.PUR_AGENT,Level.LEVEL_FIVE,"进货商1","15247678373",
					"南京新街口","421001","dds@163.com",1.0,0.0,2000.0,"业务员2",224.0,500));
		
			ArrayList<CustomerPO> cus=customerImpl.show();
			System.out.println("共有"+cus.size()+"记录：");
			for(CustomerPO po:cus){
				System.out.println(po.getCustomerID()+" "+po.getCustomerName()+" "+po.getAddress());
			}
			System.out.println("查询结束");
			
			ArrayList<CustomerPO> cus1=customerImpl.findByCustomerID(1);
			System.out.println("共有"+cus1.size()+"记录：");
			for(CustomerPO po:cus1){
				System.out.println(po.getCustomerID()+" "+po.getCustomerName()+" "+po.getAddress());
			}
			System.out.println("查询结束");
			
			customerImpl.init();
			ArrayList<CustomerPO> cus2=customerImpl.show();
			System.out.println("共有"+cus2+"记录");
			System.out.println("查询结束");
			
		}catch(RemoteException e){
			System.out.println("Exception！");
			e.printStackTrace();
		}
		end(s);
	}

	public static void main(String[] args) {
		TestDatabase test = new TestDatabase();
		
		//test.testClassification();
		//test.testGoods();
		test.testCustomer();
		
		test.sf.close();
	}

}
